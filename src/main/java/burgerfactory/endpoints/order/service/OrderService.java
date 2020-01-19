package burgerfactory.endpoints.order.service;

import burgerfactory.endpoints.order.dto.OrderDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface OrderService {
    float calculateTotalPrice(OrderDto order);
    String remitPayment(OrderDto invoice, RedirectAttributes redirectAttributes);
    OrderDto getInvoice(OrderDto invoice);
}
