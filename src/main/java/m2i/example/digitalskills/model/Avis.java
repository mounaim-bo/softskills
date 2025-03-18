package m2i.example.digitalskills.model;

import jakarta.persistence.*;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur user;

    @ManyToOne
    private Produit produit;

    private Integer rating;
    private String comment;

    public Avis(Long id, Utilisateur user, Produit produit, Integer rating, String comment) {
        this.id = id;
        this.user = user;
        this.produit = produit;
        this.rating = rating;
        this.comment = comment;
    }

    public Avis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
