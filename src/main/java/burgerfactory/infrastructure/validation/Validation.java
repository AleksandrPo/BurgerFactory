package burgerfactory.infrastructure.validation;

import burgerfactory.endpoints.order.dto.OrderDto;

import java.security.Principal;

public interface Validation {
    boolean hasRightsToGetInvoice(OrderDto invoice, Principal principal);
}
