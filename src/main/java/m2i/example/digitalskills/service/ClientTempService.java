package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.ClientTemp;

public interface ClientTempService {
    public String generateClientId();

    public ClientTemp createTempClient();

    public ClientTemp getOrCreateTempClient(String clientId);

    public boolean existsByClientId(String clientId);
}
