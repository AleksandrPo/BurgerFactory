package burgerfactory.endpoints.order.payment.strategy.impl;

import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.infrastructure.messages.Messages;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class CreditCardStrategy implements PaymentStrategy {

    private String firstname;
    private String lastname;
    private Long cardNumber;
    private String expirationDate;
    private Short cvcCode;

    private CreditCardStrategy() {
    }

    @Override
    public String pay(float amount, RedirectAttributes redirectAttributes) {
        if (isCreditCardValid()) {
            redirectAttributes.addFlashAttribute("paymentSuccessfulMessage", Messages.PAYMENT_SUCCESSFUL.message);
            return "redirect:/burgerFactory/main";
        } else {
            redirectAttributes.addFlashAttribute("paymentFailureMessage", Messages.PAYMENT_STRATEGY_FAILURE.message);
            return "redirect:/order/getInvoice";
        }
    }

    public static class Builder {

        private String firstname;
        private String lastname;
        private Long cardNumber;
        private String expirationDate;
        private Short cvcCode;

        public Builder() {
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setCardNumber(Long cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder setCvcCode(short cvcCode) {
            this.cvcCode = cvcCode;
            return this;
        }

        public CreditCardStrategy build() {
            CreditCardStrategy ccStrategy = new CreditCardStrategy();
            ccStrategy.firstname = this.firstname;
            ccStrategy.lastname = this.lastname;
            ccStrategy.cardNumber = this.cardNumber;
            ccStrategy.expirationDate = this.expirationDate;
            ccStrategy.cvcCode = this.cvcCode;
            return ccStrategy;
        }
    }

    private boolean isCreditCardValid() {
        return firstname != null
                && lastname != null
                && cardNumber != null
                && expirationDate != null
                && cvcCode != null;
    }

    public String getFirstname() {
        return firstname;
    }

    private void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    private void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    private void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Short getCvcCode() {
        return cvcCode;
    }

    private void setCvcCode(Short cvcCode) {
        this.cvcCode = cvcCode;
    }
}
