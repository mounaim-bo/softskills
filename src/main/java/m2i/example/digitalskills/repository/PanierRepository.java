package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {
}
