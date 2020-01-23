package burgerfactory.infrastructure.enums;

public enum  Variables {

    PAYPAL_STRATEGY("PAYPAL"),
    CREDIT_CARD_STRATEGY("CREDIT_CARD"),
    INVOICE("invoice");

    public final String get;

    private Variables(String get) {
        this.get = get;
    }
}
