package burgerfactory.endpoints.order.payment.strategy;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface PaymentStrategy {
    String pay(float amount, RedirectAttributes redirectAttributes);
}
