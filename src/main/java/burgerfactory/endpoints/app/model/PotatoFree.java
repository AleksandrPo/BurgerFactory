package burgerfactory.endpoints.app.model;

import burgerfactory.endpoints.order.model.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PotatoFree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String potatoSize;
    private short qty;
    private float price;

    @ManyToOne
    @JoinColumn(name = "potato_free_menu")
    private Menu potatoFreeMenu;

    @ManyToMany(mappedBy = "potatoFrees")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPotatoSize() {
        return potatoSize;
    }

    public void setPotatoSize(String potatoSize) {
        this.potatoSize = potatoSize;
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

    public Menu getPotatoFreeMenu() {
        return potatoFreeMenu;
    }

    public void setPotatoFreeMenu(Menu potatoFreeMenu) {
        this.potatoFreeMenu = potatoFreeMenu;
    }
}
