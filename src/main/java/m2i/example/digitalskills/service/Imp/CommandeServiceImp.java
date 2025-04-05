package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Commande;
import m2i.example.digitalskills.repository.CommandeRepository;
import m2i.example.digitalskills.service.CommandeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommandeServiceImp implements CommandeService {

    private CommandeRepository commandeRepository;

    public CommandeServiceImp(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public Commande createCommande(Commande commande){
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Commande commande){
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Commande commande){
        commandeRepository.delete(commande);
    }

    public List<Commande> getCommandes(){
        return commandeRepository.findAll();
    }

    public Commande findOne(Long id){
        Optional<Commande> commande = commandeRepository.findById(id);
        return commande.orElseThrow(() -> new RuntimeException("Commande n'existe pas"));
    }
}
