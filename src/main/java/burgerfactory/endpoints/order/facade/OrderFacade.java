package burgerfactory.endpoints.order.facade;

import burgerfactory.endpoints.order.dto.OrderDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface OrderFacade {
    float getTotalPrice(OrderDto invoice);
    String remitPayment(OrderDto invoice, RedirectAttributes redirectAttributes);
    OrderDto getInvoice(OrderDto invoice);
}
