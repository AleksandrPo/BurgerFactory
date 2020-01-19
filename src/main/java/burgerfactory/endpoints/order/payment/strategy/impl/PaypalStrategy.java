package burgerfactory.endpoints.order.payment.strategy.impl;

import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.infrastructure.messages.Messages;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class PaypalStrategy implements PaymentStrategy {

    private String email;
    private String password;

    public PaypalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String pay(float amount, RedirectAttributes redirectAttributes) {
        if (isPaypalAccountValid()) {
            redirectAttributes.addFlashAttribute("paymentSuccessfulMessage", Messages.PAYMENT_SUCCESSFUL.message);
            return "redirect:/burgerFactory/main";
        } else {
            redirectAttributes.addFlashAttribute("paymentFailureMessage", Messages.PAYMENT_STRATEGY_FAILURE.message);
            return "redirect:/order/getInvoice";
        }
    }

    private boolean isPaypalAccountValid() {
        return email != null
                && email.contains("@")
                && email.contains(".")
                && password != null;
    }
}
