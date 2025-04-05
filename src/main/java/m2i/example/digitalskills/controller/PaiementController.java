package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Paiement;
import m2i.example.digitalskills.service.PaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiement")
public class PaiementController {

    private PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement){
        return new ResponseEntity<>(paiementService.createPaiement(paiement), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement){
        return new ResponseEntity<>(paiementService.updatePaiement(paiement), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deletePaiement(@RequestBody Paiement paiement){
        paiementService.deletePaiement(paiement);
    }

    @GetMapping
    public List<Paiement> getPaiements(){
        return paiementService.getPaiements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> findOne(@PathVariable Long id){
        return ResponseEntity.ok(paiementService.findOne(id));
    }
}
