package burgerfactory.endpoints.app;

import burgerfactory.endpoints.app.facade.AppFacade;
import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.service.UserService;
import burgerfactory.endpoints.app.model.*;
import burgerfactory.endpoints.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/burgerFactory")
public class ApplicationEndpont {

    @Autowired
    private AppFacade appFacade;
    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String mainMenu(Model model) {
        model.addAttribute("order", appFacade.prepareNewOrder((Menu) model.asMap().get("menu")));
        return "mainPage";
    }

    @ModelAttribute("menu")
    public Menu getMenu() {
        return appFacade.getMenu();
    }

    @PostMapping("/calculateTotalPrice")
    public String startOrderProcessing(ModelMap model, @ModelAttribute("order") OrderDto order, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("invoice", order);
        return "redirect:/order/getInvoice";
    }

    @GetMapping("/userInfo")
    public String userInfoPage(Model model, Principal principal) {
        UserDto user = userService.findByUsername(principal.getName());
        model.addAttribute("userInfo", user);
        return "/userInfo";
    }

    @PostMapping("/userInfo")
    public String updateUserInfo (@ModelAttribute("userInfo") UserDto user) {
        userService.save(user);
        return "/userInfo";
    }
}
