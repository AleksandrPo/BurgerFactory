package burgerfactory.endpoints.order.facade.impl;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.facade.OrderFacade;
import burgerfactory.endpoints.order.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderServiceImpl orderService;

    @Override
    public float getTotalPrice(OrderDto invoice) {
        return orderService.calculateTotalPrice(invoice);
    }

    @Override
    public String remitPayment(OrderDto invoice) {
        return orderService.remitPayment(invoice);
    }
}
