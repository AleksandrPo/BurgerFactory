package burgerfactory.endpoints.order.model;

import burgerfactory.endpoints.app.model.Burger;
import burgerfactory.endpoints.app.model.Drink;
import burgerfactory.endpoints.app.model.PotatoFree;

import javax.persistence.*;
import java.util.Set;

//@Entity
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Set<Burger> burgers;

    @ManyToMany
    private Set<Drink> drinks;

    @ManyToMany
    private Set<PotatoFree> potatoFrees;

    @OneToOne(mappedBy = "productList")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
