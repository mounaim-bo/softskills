package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Panier;

import java.util.List;

public interface PanierService {

    Panier createPanier(Panier panier);

    Panier updatePanier(Panier panier);

    void deletePanier(Panier panier);

    List<Panier> getPaniers();

    Panier findOne(Long id);
}
