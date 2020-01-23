package burgerfactory.endpoints.order.factory.impl;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.factory.PaymentFactory;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.CreditCardStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.PaypalStrategy;
import burgerfactory.infrastructure.enums.Variables;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class PaymentFactoryImpl implements PaymentFactory {

    @Override
    public PaymentStrategy getPaymentStrategy(String strategy, OrderDto invoice, RedirectAttributes redirectAttributes) {
        if (strategy.equals(Variables.CREDIT_CARD_STRATEGY.get)) {
            return useCreditCardStrategy(invoice, redirectAttributes);
        } else if (strategy.equals(Variables.PAYPAL_STRATEGY.get)) {
            return usePaypalStrategy(invoice, redirectAttributes);
        }
        return null;
    }

    private PaymentStrategy useCreditCardStrategy(OrderDto invoice, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(Variables.INVOICE.get, invoice);
        return new CreditCardStrategy.Builder()
                .setFirstname(invoice.getPaymentStrategy().getFirstName())
                .setLastname(invoice.getPaymentStrategy().getLastName())
                .setCardNumber(invoice.getPaymentStrategy().getCardNumber())
                .setExpirationDate(invoice.getPaymentStrategy().getExpirationDate())
                .setCvcCode(invoice.getPaymentStrategy().getCvcCode())
                .build();
    }

    private PaymentStrategy usePaypalStrategy(OrderDto invoice, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(Variables.INVOICE.get, invoice);
        return new PaypalStrategy(invoice.getPaymentStrategy().getEmail(), invoice.getPaymentStrategy().getPassword());
    }
}
