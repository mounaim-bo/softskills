package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.dto.ClientTempResponse;
import m2i.example.digitalskills.model.ClientTemp;
import m2i.example.digitalskills.repository.ClientTempRepository;
import m2i.example.digitalskills.service.ClientTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientTempService clientTempService;

    @Autowired
    private ClientTempRepository clientTempRepository;

    /**
     * Endpoint appelé quand un client entre dans l'application
     */
    @PostMapping("/enter")
    public ResponseEntity<ClientTempResponse> clientEnter(
            @RequestParam(required = false) String existingClientId) {

        ClientTemp clientTemp;

        if (existingClientId != null && clientTempService.existsByClientId(existingClientId)) {
            // Client existant, on récupère ses infos
            clientTemp = clientTempService.getOrCreateTempClient(existingClientId);
        } else {
            // Nouveau client, on crée un nouvel enregistrement
            clientTemp = clientTempService.createTempClient();
        }

        ClientTempResponse response = new ClientTempResponse(
                clientTemp.getClientId(),
                clientTemp.getDateCreation(),
                existingClientId == null ? "Nouveau client créé" : "Client existant récupéré"
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint pour vérifier le statut d'un client
     */
    @GetMapping("/status/{clientId}")
    public ResponseEntity<ClientTemp> getClientStatus(@PathVariable String clientId) {
        Optional<ClientTemp> clientTemp = clientTempRepository.findByClientId(clientId);

        if (clientTemp.isPresent()) {
            return ResponseEntity.ok(clientTemp.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
