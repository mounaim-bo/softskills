package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Commande;
import m2i.example.digitalskills.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande){
        return new ResponseEntity<>(commandeService.createCommande(commande), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande){
        return new ResponseEntity<>(commandeService.updateCommande(commande), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteCommande(@RequestBody Commande commande){
        commandeService.deleteCommande(commande);
    }

    @GetMapping
    public List<Commande> getCommandes(){
        return commandeService.getCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> findOne(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.findOne(id));
    }
}
