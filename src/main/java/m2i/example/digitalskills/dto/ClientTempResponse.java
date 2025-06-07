package m2i.example.digitalskills.dto;

import java.time.LocalDateTime;

public class ClientTempResponse {
    private String clientId;
    private LocalDateTime dateCreation;
    private String message;

    public ClientTempResponse(String clientId, LocalDateTime dateCreation, String message) {
        this.clientId = clientId;
        this.dateCreation = dateCreation;
        this.message = message;
    }

    // Getters et setters
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
