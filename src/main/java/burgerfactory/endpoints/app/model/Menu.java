package burgerfactory.endpoints.app.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @OneToMany(mappedBy = "burgerMenu")
    private List<Burger> burgersMenu;

    @OneToMany(mappedBy = "drinkMenu")
    private List<Drink> drinksMenu;

    @OneToMany(mappedBy = "potatoFreeMenu")
    private List<PotatoFree> potatoFreesMenu;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public List<Burger> getBurgersMenu() {
        return burgersMenu;
    }

    public void setBurgersMenu(List<Burger> burgersMenu) {
        this.burgersMenu = burgersMenu;
    }

    public List<Drink> getDrinksMenu() {
        return drinksMenu;
    }

    public void setDrinksMenu(List<Drink> drinksMenu) {
        this.drinksMenu = drinksMenu;
    }

    public List<PotatoFree> getPotatoFreesMenu() {
        return potatoFreesMenu;
    }

    public void setPotatoFreesMenu(List<PotatoFree> potatoFreesMenu) {
        this.potatoFreesMenu = potatoFreesMenu;
    }
}
