package burgerfactory.endpoints.order;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.facade.impl.OrderFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderEndpoint {

    @Autowired
    private OrderFacadeImpl orderFacade;

    @GetMapping("/")
    public String getOrderPage() {
        return "/orderPage";
    }

    @GetMapping("/getInvoice")
    public String getInvoice(Model model, @ModelAttribute("invoice") OrderDto invoice, Principal principal) {
        model.addAttribute("invoice", orderFacade.getInvoice(invoice, principal));
        return "/orderPage";
    }

    @PostMapping("/approveAndPay")
    public String pay(@ModelAttribute("invoice") OrderDto invoice, RedirectAttributes redirectAttributes) {
        return orderFacade.remitPayment(invoice, redirectAttributes);
    }

    @GetMapping("/decline")
    public String decline() {
        return "redirect:/burgerFactory/main";
    }
}
