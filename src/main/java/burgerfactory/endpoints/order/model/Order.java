package burgerfactory.endpoints.order.model;

import burgerfactory.endpoints.auth.model.User;
import burgerfactory.endpoints.app.model.Burger;
import burgerfactory.endpoints.app.model.Drink;
import burgerfactory.endpoints.app.model.PotatoFree;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "burger_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "burger_id"))
    private Set<Burger> burgers;

    @ManyToMany
    @JoinTable(name = "drink_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id"))
    private Set<Drink> drinks;

    @ManyToMany
    @JoinTable(name = "potato_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "potato_id"))
    private Set<PotatoFree> potatoFrees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Burger> getBurgers() {
        return burgers;
    }

    public void setBurgers(Set<Burger> burgers) {
        this.burgers = burgers;
    }

    public Set<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    public Set<PotatoFree> getPotatoFrees() {
        return potatoFrees;
    }

    public void setPotatoFrees(Set<PotatoFree> potatoFrees) {
        this.potatoFrees = potatoFrees;
    }
}
