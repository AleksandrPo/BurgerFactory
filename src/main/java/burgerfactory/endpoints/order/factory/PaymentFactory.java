package burgerfactory.endpoints.order.factory;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface PaymentFactory {
    PaymentStrategy getPaymentStrategy(String strategy, OrderDto invoice, RedirectAttributes redirectAttributes);
}
