package m2i.example.digitalskills.service.Imp;

import jakarta.transaction.Transactional;
import m2i.example.digitalskills.model.ClientTemp;
import m2i.example.digitalskills.repository.ClientTempRepository;
import m2i.example.digitalskills.service.ClientTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ClientTempServiceImp implements ClientTempService {

    @Autowired
    private ClientTempRepository clientTempRepository;

    @Override
    public String generateClientId() {
        return "TEMP_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
    }

    @Override
    public ClientTemp createTempClient() {
        ClientTemp clientTemp = new ClientTemp();
        clientTemp.setClientId(generateClientId());
        clientTemp.setDateCreation(LocalDateTime.now());

        return clientTempRepository.save(clientTemp);
    }

    @Override
    public ClientTemp getOrCreateTempClient(String clientId) {
        if (clientId != null) {
            Optional<ClientTemp> existing = clientTempRepository.findByClientId(clientId);
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        return createTempClient();
    }

    @Override
    public boolean existsByClientId(String clientId) {
        return clientTempRepository.findByClientId(clientId).isPresent();
    }
}
