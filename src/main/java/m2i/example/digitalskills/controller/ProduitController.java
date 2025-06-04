package m2i.example.digitalskills.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import m2i.example.digitalskills.model.Categorie;
import m2i.example.digitalskills.model.Produit;
import m2i.example.digitalskills.service.CategorieService;
import m2i.example.digitalskills.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private ProduitService produitService;
    private CategorieService categorieService;

    public ProduitController(ProduitService produitService, CategorieService categorieService) {
        this.produitService = produitService;
        this.categorieService = categorieService;
    }

    @PostMapping
    public ResponseEntity<?> createProduit(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("prix") BigDecimal prix,
            @RequestParam("stock_quantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "modelFile", required = false) MultipartFile modelFile) {

        try {
            // Get category by ID
            Categorie category = categorieService.findOne(categoryId);

            // Handle image file
            String imagePath = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                // Create unique filename
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                // Path where to save the image
                Path uploadPath = Paths.get("uploads");

                // Create directory if it doesn't exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Store the relative path
                imagePath = "/uploads/" + uniqueFileName;
            }

            //Handle model file
            String modelPath = null;
            if (modelFile != null && !modelFile.isEmpty()) {
                // Create unique filename
                String fileName = StringUtils.cleanPath(modelFile.getOriginalFilename());
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                // Path where to save the image
                Path uploadPath = Paths.get("uploads");

                // Create directory if it doesn't exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(modelFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Store the relative path
                modelPath = "/uploads/" + uniqueFileName;
            }

            // Create and save product
            Produit produit = new Produit();
            produit.setName(name);
            produit.setDescription(description);
            produit.setPrix(prix);
            produit.setStockQuantity(stockQuantity);
            produit.setCategory(category);
            produit.setImageUrl(imagePath);
            produit.setModelUrl(modelPath);

            Produit savedProduit = produitService.createProduit(produit);

            return ResponseEntity.ok().body(savedProduit);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Erreur lors de l'ajout du produit: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduit(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("prix") BigDecimal prix,
            @RequestParam("stock_quantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "modelFile", required = false) MultipartFile modelFile) {

        try {
            // Vérifier si le produit existe
            Produit existingProduit = produitService.findOne(id);
            if (existingProduit == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Produit non trouvé avec l'ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Get category by ID
            Categorie category = categorieService.findOne(categoryId);

            // Handle image file - seulement si un nouveau fichier est fourni
            String imagePath = existingProduit.getImageUrl(); // Conserver l'ancienne image par défaut
            if (imageFile != null && !imageFile.isEmpty()) {
                // Create unique filename
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                // Path where to save the image
                Path uploadPath = Paths.get("uploads");

                // Create directory if it doesn't exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Store the relative path
                imagePath = "/uploads/" + uniqueFileName;
            }

            // Handle model file - seulement si un nouveau fichier est fourni
            String modelPath = existingProduit.getModelUrl(); // Conserver l'ancien modèle par défaut
            if (modelFile != null && !modelFile.isEmpty()) {
                // Create unique filename
                String fileName = StringUtils.cleanPath(modelFile.getOriginalFilename());
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                // Path where to save the file
                Path uploadPath = Paths.get("uploads");

                // Create directory if it doesn't exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(modelFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Store the relative path
                modelPath = "/uploads/" + uniqueFileName;
            }

            // Mettre à jour les propriétés du produit existant
            existingProduit.setName(name);
            existingProduit.setDescription(description);
            existingProduit.setPrix(prix);
            existingProduit.setStockQuantity(stockQuantity);
            existingProduit.setCategory(category);
            existingProduit.setImageUrl(imagePath);
            existingProduit.setModelUrl(modelPath);

            // Sauvegarder les modifications
            Produit updatedProduit = produitService.updateProduit(existingProduit);

            return ResponseEntity.ok().body(updatedProduit);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Erreur lors de la modification du produit: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping
    public void deleteProduit(@RequestBody Produit produit){
        produitService.deleteProduit(produit);
    }

    @GetMapping
    public List<Produit> getProduits(){
        return produitService.getProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> findOne(@PathVariable Long id){
        return ResponseEntity.ok(produitService.findOne(id));
    }

    @GetMapping("/categorie/{id}")
    public List<Produit> getProduitsByCategorie(@PathVariable("id") Long categorieId) {
        return produitService.getProduitsByCategorie(categorieId);
    }
}