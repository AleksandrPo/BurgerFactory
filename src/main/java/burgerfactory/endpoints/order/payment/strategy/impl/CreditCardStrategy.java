package burgerfactory.endpoints.order.payment.strategy.impl;

import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;

public class CreditCardStrategy implements PaymentStrategy {

    private String firstname;
    private String lastname;
    private Long cardNumber;
    private String expirationDate;
    private Short cvcCode;

    public CreditCardStrategy(String firstname, String lastname, Long cardNumber, String expirationDate, short cvcCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvcCode = cvcCode;
    }

    @Override
    public String pay(float amount) {
        return validateCard() ? "SUCCESS" : "FAILED";
    }

    private boolean validateCard() {
        return firstname != null
                && lastname != null
                && cardNumber != null
                && expirationDate != null
                && cvcCode != null;
    }
}
