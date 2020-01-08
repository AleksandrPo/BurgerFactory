package burgerfactory.endpoints.app.model;

import javax.persistence.*;

@Entity
public class BurgerFilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean cheese;
    private Boolean porkMeat;
    private Boolean chickenMeat;
    private Boolean tomato;
    private Boolean avocado;
    private Boolean dill;
    private Boolean cabbage;
    private Boolean cucumber;
    private Boolean ketchup;
    private Boolean mayonnaise;
    private Boolean mustard;

    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger burger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCheese() {
        return cheese;
    }

    public void setCheese(Boolean cheese) {
        this.cheese = cheese;
    }

    public Boolean getPorkMeat() {
        return porkMeat;
    }

    public void setPorkMeat(Boolean porkMeat) {
        this.porkMeat = porkMeat;
    }

    public Boolean getChickenMeat() {
        return chickenMeat;
    }

    public void setChickenMeat(Boolean chickenMeat) {
        this.chickenMeat = chickenMeat;
    }

    public Boolean getTomato() {
        return tomato;
    }

    public void setTomato(Boolean tomato) {
        this.tomato = tomato;
    }

    public Boolean getAvocado() {
        return avocado;
    }

    public void setAvocado(Boolean avocado) {
        this.avocado = avocado;
    }

    public Boolean getDill() {
        return dill;
    }

    public void setDill(Boolean dill) {
        this.dill = dill;
    }

    public Boolean getCabbage() {
        return cabbage;
    }

    public void setCabbage(Boolean cabbage) {
        this.cabbage = cabbage;
    }

    public Boolean getCucumber() {
        return cucumber;
    }

    public void setCucumber(Boolean cucumber) {
        this.cucumber = cucumber;
    }

    public Boolean getKetchup() {
        return ketchup;
    }

    public void setKetchup(Boolean ketchup) {
        this.ketchup = ketchup;
    }

    public Boolean getMayonnaise() {
        return mayonnaise;
    }

    public void setMayonnaise(Boolean mayonnaise) {
        this.mayonnaise = mayonnaise;
    }

    public Boolean getMustard() {
        return mustard;
    }

    public void setMustard(Boolean mustard) {
        this.mustard = mustard;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }
}
