package m2i.example.digitalskills.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur user;

    @OneToMany
    private List<Produit> products;

    private BigDecimal totalPrice;

    public Panier(Long id, Utilisateur user, List<Produit> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public Panier() {
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

    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }
}
