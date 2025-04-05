package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Paiement;
import m2i.example.digitalskills.repository.PaiementRepository;
import m2i.example.digitalskills.service.PaiementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceIml implements PaiementService {

    private PaiementRepository paiementRepository;

    public PaiementServiceIml(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public void deletePaiement(Paiement paiement) {
        paiementRepository.delete(paiement);
    }

    @Override
    public List<Paiement> getPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement findOne(Long id) {
        Optional<Paiement> paiement = paiementRepository.findById(id);
        return paiement.orElseThrow(() -> new RuntimeException("Paiement n'existe pas"));
    }
}
