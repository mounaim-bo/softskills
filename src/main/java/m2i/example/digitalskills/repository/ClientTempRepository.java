package m2i.example.digitalskills.repository;


import m2i.example.digitalskills.model.ClientTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientTempRepository extends JpaRepository<ClientTemp, String> {
    Optional<ClientTemp> findByClientId(String clientId);
}
