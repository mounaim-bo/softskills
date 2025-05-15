package m2i.example.digitalskills.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import m2i.example.digitalskills.model.Categorie;
import m2i.example.digitalskills.model.Produit;
import m2i.example.digitalskills.service.CategorieService;
import m2i.example.digitalskills.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.ErrorResponse;
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
            @RequestParam("price") BigDecimal price,
            @RequestParam("stock_quantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

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

            // Create and save product
            Produit produit = new Produit();
            produit.setName(name);
            produit.setDescription(description);
            produit.setPrice(price);
            produit.setStockQuantity(stockQuantity);
            produit.setCategory(category);
            produit.setImageUrl(imagePath);

            Produit savedProduit = produitService.createProduit(produit);

            return ResponseEntity.ok().body(savedProduit);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Erreur lors de l'ajout du produit: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<Produit> updateProduit(@RequestBody Produit produit){
        return new ResponseEntity<>(produitService.updateProduit(produit), HttpStatus.CREATED);
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
