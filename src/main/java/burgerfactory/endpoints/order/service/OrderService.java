package burgerfactory.endpoints.order.service;

import burgerfactory.endpoints.order.dto.OrderDto;

public interface OrderService {
    float calculateTotalPrice(OrderDto order);
    String remitPayment(OrderDto invoice);
}
