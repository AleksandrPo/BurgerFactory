package burgerfactory.endpoints.order.payment.strategy.impl;

import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;

public class PaypalStrategy implements PaymentStrategy {

    private String email;
    private String password;

    public PaypalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String pay(float amount) {
        return validatePaypalAccount() ? "SUCCESS" : "FAILED";
    }

    private boolean validatePaypalAccount() {
        return email != null
                && email.contains("@")
                && email.contains(".")
                && password != null;
    }
}
