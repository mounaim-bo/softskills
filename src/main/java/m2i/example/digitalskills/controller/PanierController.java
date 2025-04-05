package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.service.PanierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    private PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @PostMapping
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier){
        return new ResponseEntity<>(panierService.createPanier(panier), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Panier> updatePanier(@RequestBody Panier panier){
        return new ResponseEntity<>(panierService.updatePanier(panier), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deletePanier(@RequestBody Panier panier){
        panierService.deletePanier(panier);
    }

    @GetMapping
    public List<Panier> getPaniers(){
        return panierService.getPaniers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> findOne(@PathVariable Long id){
        return ResponseEntity.ok(panierService.findOne(id));
    }
}
