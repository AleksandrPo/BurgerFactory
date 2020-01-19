package burgerfactory.infrastructure.validation.impl;

import burgerfactory.endpoints.auth.service.UserService;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.infrastructure.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ValidationImpl implements Validation {

    @Autowired
    private UserService userService;

    @Override
    public boolean hasRightsToGetInvoice(OrderDto invoice, Principal principal) {
        return invoice != null && userService.isRegisteredUser(principal.getName());
    }
}
