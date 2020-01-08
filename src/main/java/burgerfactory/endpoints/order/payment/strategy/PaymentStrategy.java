package burgerfactory.endpoints.order.payment.strategy;

public interface PaymentStrategy {
    String pay(float amount);
}
