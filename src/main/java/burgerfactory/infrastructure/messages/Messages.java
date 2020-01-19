package burgerfactory.infrastructure.messages;

public enum  Messages {
    PAYMENT_SUCCESSFUL("YOUR ORDER APPROVED AND IS ALREADY ON THE WAY TO YOU"),
    PAYMENT_STRATEGY_FAILURE("PLEASE FILL ALL EMPTY FIELDS"),
    PRODUCT_NOT_SELECTED("HEY, YOU FORGOT TO CHOOSE A PRODUCT, TRY ONCE MORE");

    public final String message;

    private Messages(String message) {
        this.message = message;
    }
}
