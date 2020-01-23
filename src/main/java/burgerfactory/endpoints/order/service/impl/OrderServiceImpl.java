package burgerfactory.endpoints.order.service.impl;

import burgerfactory.endpoints.app.model.Burger;
import burgerfactory.endpoints.app.model.Drink;
import burgerfactory.endpoints.app.model.PotatoFree;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.dto.StrategyDto;
import burgerfactory.endpoints.order.factory.PaymentFactory;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.endpoints.order.service.OrderService;
import burgerfactory.infrastructure.enums.Messages;
import burgerfactory.infrastructure.enums.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PaymentFactory paymentFactory;

    @Override
    public float calculateTotalPrice(OrderDto order) {
        return getBurgerTotalPrice(order.getBurgers()) +
                getDrinkTotalPrice(order.getDrinks()) +
                getPotatoTotalPrice(order.getPotatoFrees());
    }

    @Override
    public String remitPayment(OrderDto invoice, RedirectAttributes redirectAttributes) {
        String selectedPaymentStrategy = Optional.of(invoice).map(OrderDto::getPaymentStrategy).map(StrategyDto::getStrategy).orElse("");
        if (isInvoiceEmpty(invoice)) {
            return getFailureRedirectPage(Messages.PRODUCT_NOT_SELECTED.message, invoice, redirectAttributes);
        }
        return selectPaymentStrategyAndPay(selectedPaymentStrategy, invoice, redirectAttributes);
    }

    @Override
    public OrderDto getInvoice(OrderDto invoice) {
        if (invoice != null) {
            invoice.setTotalPrice(calculateTotalPrice(invoice));
        }
        return invoice;
    }

    private String selectPaymentStrategyAndPay(String selectedPaymentStrategy, OrderDto invoice, RedirectAttributes redirectAttributes) {
        PaymentStrategy paymentStrategy;
        try {
            paymentStrategy = paymentFactory.getPaymentStrategy(selectedPaymentStrategy, invoice, redirectAttributes);
        } catch (NullPointerException e) {
            return getFailureRedirectPage(Messages.PAYMENT_STRATEGY_FAILURE.message, invoice, redirectAttributes);
        }
        if (paymentStrategy == null) {
            return getFailureRedirectPage(Messages.PAYMENT_STRATEGY_FAILURE.message, invoice, redirectAttributes);
        }
        return paymentStrategy.pay(invoice.getTotalPrice(), redirectAttributes);
    }

    private boolean isInvoiceEmpty(OrderDto invoice) {
        return Optional.of(invoice).map(OrderDto::getBurgers).map(List::isEmpty).orElse(true)
                && Optional.of(invoice).map(OrderDto::getDrinks).map(List::isEmpty).orElse(true)
                && Optional.of(invoice).map(OrderDto::getPotatoFrees).map(List::isEmpty).orElse(true);
    }

    private String getFailureRedirectPage(String message, OrderDto invoice, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(Variables.INVOICE.get, invoice);
        redirectAttributes.addFlashAttribute("paymentFailureMessage", message);
        return Messages.PAYMENT_STRATEGY_FAILURE.message.equals(message) ? "redirect:/order/getInvoice" : "redirect:/burgerFactory/main";
    }

    private float getBurgerTotalPrice(List<Burger> burgers) {
        float price = 0f;
        if (burgers != null && !burgers.isEmpty()) {
            for (Burger burger : burgers) {
                price += burger.getPrice() * burger.getQty();
            }
        }
        return price;
    }
    private float getDrinkTotalPrice(List<Drink> drinks) {
        float price = 0f;
        if (drinks != null && !drinks.isEmpty()) {
            for (Drink drink : drinks) {
                price += drink.getPrice() * drink.getQty();
            }
        }
        return price;
    }
    private float getPotatoTotalPrice(List<PotatoFree> potatoFrees) {
        float price = 0f;
        if (potatoFrees != null && !potatoFrees.isEmpty()) {
            for (PotatoFree potatoFree : potatoFrees) {
                price += potatoFree.getPrice() * potatoFree.getQty();
            }
        }
        return price;
    }
}
