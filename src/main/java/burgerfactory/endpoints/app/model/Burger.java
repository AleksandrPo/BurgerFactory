package burgerfactory.endpoints.app.model;

import burgerfactory.endpoints.order.model.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private short qty;

    @ManyToOne
    @JoinColumn(name = "burger_menu")
    private Menu burgerMenu;

    @OneToMany(mappedBy = "burger")
    private Set<BurgerFilling> burgerFillings;

    @ManyToMany(mappedBy = "burgers")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<BurgerFilling> getBurgerFillings() {
        return burgerFillings;
    }

    public void setBurgerFillings(Set<BurgerFilling> burgerFillings) {
        this.burgerFillings = burgerFillings;
    }

    public Menu getBurgerMenu() {
        return burgerMenu;
    }

    public void setBurgerMenu(Menu burgerMenu) {
        this.burgerMenu = burgerMenu;
    }

    public short getQty() {
        return qty;
    }

    public void setQty(short qty) {
        this.qty = qty;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
