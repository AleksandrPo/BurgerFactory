package burgerfactory.endpoints.order.facade.impl;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.facade.OrderFacade;
import burgerfactory.endpoints.order.service.impl.OrderServiceImpl;
import burgerfactory.infrastructure.validation.impl.ValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ValidationImpl validation;

    @Override
    public float getTotalPrice(OrderDto invoice) {
        return orderService.calculateTotalPrice(invoice);
    }

    @Override
    public String remitPayment(OrderDto invoice, RedirectAttributes redirectAttributes) {
        return orderService.remitPayment(invoice, redirectAttributes);
    }

    @Override
    public OrderDto getInvoice(OrderDto invoice, Principal principal) {
//        validation.hasRightsToGetInvoice(invoice, principal);// TODO: add proper validation
        if (validation.hasRightsToGetInvoice(invoice, principal)) {
            return orderService.getInvoice(invoice);
        }
        return orderService.getInvoice(invoice);
    }
}
