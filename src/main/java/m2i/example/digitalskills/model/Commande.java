package m2i.example.digitalskills.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "client_id")
    private Client client;

    @Column(name = "date_commande")
    private LocalDateTime dateCommande;
    private String status;

    @Column(name = "montant_total")
    private BigDecimal montantTotal;

    @Column(name = "adresse_livraison", nullable = true)
    private String adresseLivraison;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes = new ArrayList<>();

    public Commande(Long id, Client client, LocalDateTime dateCommande, String status, BigDecimal montantTotal, String adresseLivraison, List<LigneCommande> ligneCommandes) {
        this.id = id;
        this.client = client;
        this.dateCommande = dateCommande;
        this.status = status;
        this.montantTotal = montantTotal;
        this.adresseLivraison = adresseLivraison;
        this.ligneCommandes = ligneCommandes;
    }

    public Commande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public void ajouterLigne(LigneCommande ligne) {
        ligneCommandes.add(ligne);
        ligne.setCommande(this);
    }

    public void supprimerLigne(LigneCommande ligne) {
        ligneCommandes.remove(ligne);
        ligne.setCommande(null);
    }

}
