package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Commande;

import java.util.List;

public interface CommandeService {

    Commande createCommande(Commande commande);

    Commande updateCommande(Commande commande);

    void deleteCommande(Commande commande);

    List<Commande> getCommandes();

    Commande findOne(Long id);
}
