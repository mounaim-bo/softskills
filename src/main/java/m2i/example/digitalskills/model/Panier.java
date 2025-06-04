package m2i.example.digitalskills.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<Produit> produits;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignes;

    @Column(name = "prix_total")

    private BigDecimal prixTotal;

    @Column(name = "date_ajout")
    private LocalDateTime dateAjout;

    public Panier(Long id, Client client, List<Produit> produits, BigDecimal prixTotal, LocalDateTime dateAjout) {
        this.id = id;
        this.client = client;
        this.produits = produits;
        this.prixTotal = prixTotal;
        this.dateAjout = dateAjout;
    }

    public Panier() {
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

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public void ajouterLigne(LignePanier ligne) {
        lignes.add(ligne);
        ligne.setPanier(this);
    }

    public void supprimerLigne(LignePanier ligne) {
        lignes.remove(ligne);
        ligne.setPanier(null);
    }

}
