package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Produit;
import m2i.example.digitalskills.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit){
        return new ResponseEntity<>(produitService.createProduit(produit), HttpStatus.CREATED);
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
