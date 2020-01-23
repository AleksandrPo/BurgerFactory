package burgerfactory.endpoints.testdata;

import burgerfactory.endpoints.app.model.Burger;
import burgerfactory.endpoints.app.model.Drink;
import burgerfactory.endpoints.app.model.Menu;
import burgerfactory.endpoints.app.model.PotatoFree;
import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.dto.StrategyDto;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.CreditCardStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.PaypalStrategy;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    private static final String CREDIT_CARD = "CREDIT_CARD";
    private static final String PAYPAL = "PAYPAL";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email@email.lv";
    private static final Integer PHONE = 1234567;

    private static final String EXPIRATION_DATE = "expirationDate";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final Long CARD_NUMBER = 1234567L;
    private static final Short CVC_CODE = 123;

    public static UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setUsername(USERNAME);
        userDto.setPassword(PASSWORD);
        userDto.setEmail(EMAIL);
        userDto.setPhone(PHONE);
        return userDto;
    }

    public static User getUser() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        user.setPhone(PHONE);
        return user;
    }

    public static Principal getPrincipal(String principalName) {
        return () -> principalName;
    }

    public static OrderDto getOrderDto(String strategy) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUsername(USERNAME);
        orderDto.setPaymentStrategy(getStrategyDto(strategy));
        orderDto.setBurgers(getBurgerList());
        orderDto.setDrinks(getDrinkList());
        orderDto.setPotatoFrees(getPotatoFreeList());
        return orderDto;
    }

    public static PaymentStrategy getPaymentStrategy(String strategy) {
        PaymentStrategy paymentStrategy;
        if (strategy.equals(CREDIT_CARD)) {
            paymentStrategy = new CreditCardStrategy.Builder()
                    .setCardNumber(CARD_NUMBER)
                    .setFirstname(FIRST_NAME)
                    .setLastname(LAST_NAME)
                    .setExpirationDate(EXPIRATION_DATE)
                    .setCvcCode(CVC_CODE)
                    .build();
        } else if (strategy.equals(PAYPAL)) {
            paymentStrategy = new PaypalStrategy(EMAIL, PASSWORD);
        } else {
            paymentStrategy = null;
        }
        return paymentStrategy;
    }

    public static Menu getMenu() {
        Menu menu = new Menu();
        menu.setId((short) 1);
        menu.setBurgersMenu(getBurgerList());
        menu.setDrinksMenu(getDrinkList());
        menu.setPotatoFreesMenu(getPotatoFreeList());
        return menu;
    }

    private static List<Burger> getBurgerList() {
        List<Burger> burgers = new ArrayList<>();
        burgers.add(addBurger(5.5f, (short) 2));
        return burgers;
    }

    private static Burger addBurger(float burgerPrice, short qty) {
        Burger burger = new Burger();
        burger.setPrice(burgerPrice);
        burger.setQty(qty);
        return burger;
    }

    private static List<Drink> getDrinkList() {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(addDrink(2.5f, (short) 1));
        drinks.add(addDrink(1.5f, (short) 1));
        return drinks;
    }

    private static Drink addDrink(float drinkPrice, short qty) {
        Drink drink = new Drink();
        drink.setPrice(drinkPrice);
        drink.setQty(qty);
        return drink;
    }

    private static List<PotatoFree> getPotatoFreeList() {
        List<PotatoFree> potatoFrees = new ArrayList<>();
        potatoFrees.add(addPotato(1f, (short) 1));
        potatoFrees.add(addPotato(1.5f, (short) 2));
        potatoFrees.add(addPotato(2f, (short) 3));
        return potatoFrees;
    }

    private static PotatoFree addPotato(float potatoPrice, short qty) {
        PotatoFree potatoFree = new PotatoFree();
        potatoFree.setPrice(potatoPrice);
        potatoFree.setQty(qty);
        return potatoFree;
    }

    private static StrategyDto getStrategyDto(String strategy) {
        StrategyDto strategyDto = new StrategyDto();
        strategyDto.setStrategy(strategy);
        if (strategy.equals(CREDIT_CARD)) {
            strategyDto.setCardNumber(CARD_NUMBER);
            strategyDto.setFirstName(FIRST_NAME);
            strategyDto.setLastName(LAST_NAME);
            strategyDto.setExpirationDate(EXPIRATION_DATE);
            strategyDto.setCvcCode(CVC_CODE);
        } else if (strategy.equals(PAYPAL)) {
            strategyDto.setEmail(EMAIL);
            strategyDto.setPassword(PASSWORD);
        }
        return strategyDto;
    }
}
