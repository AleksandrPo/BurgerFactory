package burgerfactory.endpoints.order.facade;

import burgerfactory.endpoints.order.dto.OrderDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

public interface OrderFacade {
    float getTotalPrice(OrderDto invoice);
    String remitPayment(OrderDto invoice, RedirectAttributes redirectAttributes);
    OrderDto getInvoice(OrderDto invoice, Principal principal);
}
