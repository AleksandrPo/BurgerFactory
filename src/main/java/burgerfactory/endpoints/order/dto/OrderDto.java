package burgerfactory.endpoints.order.dto;

import burgerfactory.endpoints.app.model.Burger;
import burgerfactory.endpoints.app.model.Drink;
import burgerfactory.endpoints.app.model.PotatoFree;

import java.util.List;

public class OrderDto {
    private String username;
    private List<Burger> burgers;
    private List<Drink> drinks;
    private List<PotatoFree> potatoFrees;
    private StrategyDto paymentStrategy;
    private float totalPrice;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Burger> getBurgers() {
        return burgers;
    }

    public void setBurgers(List<Burger> burgers) {
        this.burgers = burgers;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<PotatoFree> getPotatoFrees() {
        return potatoFrees;
    }

    public void setPotatoFrees(List<PotatoFree> potatoFrees) {
        this.potatoFrees = potatoFrees;
    }

    public StrategyDto getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(StrategyDto paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
