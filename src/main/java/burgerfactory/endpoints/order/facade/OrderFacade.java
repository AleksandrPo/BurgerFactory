package burgerfactory.endpoints.order.facade;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.model.Order;

public interface OrderFacade {
    float getTotalPrice(OrderDto invoice);
    String remitPayment(OrderDto invoice);
}
