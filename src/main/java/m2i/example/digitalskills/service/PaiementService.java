package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Paiement;

import java.util.List;

public interface PaiementService {

    Paiement createPaiement(Paiement paiement);

    Paiement updatePaiement(Paiement paiement);

    void deletePaiement(Paiement paiement);

    List<Paiement> getPaiements();

    Paiement findOne(Long id);
}
