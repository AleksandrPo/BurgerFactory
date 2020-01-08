package burgerfactory.endpoints.order;

import burgerfactory.endpoints.auth.service.UserService;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.facade.impl.OrderFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderEndpoint {

    @Autowired
    OrderFacadeImpl orderFacade;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getOrgerPage() {
        return "/orderPage";
    }

    @GetMapping("/getInvoice")
    public String getInvoice(Model model, @ModelAttribute("invoice") OrderDto invoice, Principal principal) {
        String username = userService.getUsername(principal.getName());
//        if(username == null || orderDto != null && username != orderDto.getUsername()) return "mainPage"; //TODO: return model with validations
        if(invoice != null) {
            invoice.setTotalPrice(orderFacade.getTotalPrice(invoice));
        }
        model.addAttribute("invoice", invoice);
        return "/orderPage";
    }

    @PostMapping("/approveAndPay")
    public String pay(@ModelAttribute("invoice") OrderDto invoice) {
        return orderFacade.remitPayment(invoice);
    }

    @GetMapping("/decline")
    public String decline() {
        return "redirect:/burgerFactory/main";
    }
}
