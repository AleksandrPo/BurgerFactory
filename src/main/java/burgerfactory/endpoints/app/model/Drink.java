package burgerfactory.endpoints.app.model;

import burgerfactory.endpoints.order.model.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private short qty;
    private float price;

    @ManyToOne
    @JoinColumn(name = "drink_menu")
    private Menu drinkMenu;

    @ManyToMany(mappedBy = "drinks")
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

    public short getQty() {
        return qty;
    }

    public void setQty(short qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Menu getDrinkMenu() {
        return drinkMenu;
    }

    public void setDrinkMenu(Menu drinkMenu) {
        this.drinkMenu = drinkMenu;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
