package burgerfactory.endpoints.order.service.impl;

import burgerfactory.endpoints.app.model.Burger;
import burgerfactory.endpoints.app.model.Drink;
import burgerfactory.endpoints.app.model.PotatoFree;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.CreditCardStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.PaypalStrategy;
import burgerfactory.endpoints.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public float calculateTotalPrice(OrderDto order) {
        return getBurgerTotalPrice(order.getBurgers()) +
                getDrinkTotalPrice(order.getDrinks()) +
                getPotatoTotalPrice(order.getPotatoFrees());
    }

    @Override
    public String remitPayment(OrderDto invoice) {
        PaymentStrategy paymentStrategy;
        if (invoice.getPaymentStrategy().getStrategy().equals("CREDIT_CARD")) {
            paymentStrategy = new CreditCardStrategy(invoice.getPaymentStrategy().getFirstName(),
                    invoice.getPaymentStrategy().getLastName(),
                    invoice.getPaymentStrategy().getCardNumber(),
                    invoice.getPaymentStrategy().getExpirationDate(),
                    invoice.getPaymentStrategy().getCvcCode());
        } else if (invoice.getPaymentStrategy().getStrategy().equals("PAYPAL")) {
            paymentStrategy = new PaypalStrategy(invoice.getPaymentStrategy().getEmail(), invoice.getPaymentStrategy().getPassword());
        }  else {
            paymentStrategy = null;
        }
        return paymentStrategy == null ? null : paymentStrategy.pay(invoice.getTotalPrice());
    }

    private float getBurgerTotalPrice(List<Burger> burgers) {
        float price = 0f;
        for (Burger burger : burgers) {
            price += burger.getPrice() * burger.getQty();
        }
        return price;
    }
    private float getDrinkTotalPrice(List<Drink> drinks) {
        float price = 0f;
        for (Drink drink : drinks) {
            price += drink.getPrice() * drink.getQty();
        }
        return price;
    }
    private float getPotatoTotalPrice(List<PotatoFree> potatoFrees) {
        float price = 0f;
        for (PotatoFree potatoFree : potatoFrees) {
            price += potatoFree.getPrice() * potatoFree.getQty();
        }
        return price;
    }
}
