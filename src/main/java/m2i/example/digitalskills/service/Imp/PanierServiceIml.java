package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.repository.PanierRepository;
import m2i.example.digitalskills.service.PanierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceIml implements PanierService {

    private PanierRepository panierRepository;

    public PanierServiceIml(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    @Override
    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public Panier updatePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public void deletePanier(Panier panier) {
        panierRepository.delete(panier);
    }

    @Override
    public List<Panier> getPaniers() {
        return panierRepository.findAll();
    }

    @Override
    public Panier findOne(Long id) {
        Optional<Panier> panier = panierRepository.findById(id);
        return panier.orElseThrow(() -> new RuntimeException("Panier n'existe pas"));
    }
}
