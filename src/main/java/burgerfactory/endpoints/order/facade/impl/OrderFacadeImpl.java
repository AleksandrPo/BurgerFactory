package burgerfactory.endpoints.order.facade.impl;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.facade.OrderFacade;
import burgerfactory.endpoints.order.service.OrderService;
import burgerfactory.endpoints.order.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService orderService;

    @Override
    public float getTotalPrice(OrderDto invoice) {
        return orderService.calculateTotalPrice(invoice);
    }

    @Override
    public String remitPayment(OrderDto invoice, RedirectAttributes redirectAttributes) {
        return orderService.remitPayment(invoice, redirectAttributes);
    }

    @Override
    public OrderDto getInvoice(OrderDto invoice) {
        return orderService.getInvoice(invoice);
    }
}
