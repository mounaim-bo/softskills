package m2i.example.digitalskills.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private Integer quantity;

    @Column(name = "prix_commande")
    private BigDecimal prixCommande;

    public LigneCommande(Long id, Commande commande, Produit produit, Integer quantity, BigDecimal prixCommande) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.quantity = quantity;
        this.prixCommande = prixCommande;
    }

    public LigneCommande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrixCommande() {
        return prixCommande;
    }

    public void setPrixCommande(BigDecimal prixCommande) {
        this.prixCommande = prixCommande;
    }
}
