package burgerfactory.endpoints.auth;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.service.SecurityService;
import burgerfactory.endpoints.auth.service.UserService;
import burgerfactory.endpoints.auth.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthEndpoint {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SecurityService securityService;

    @GetMapping({"/", "/login"})
    public String loginPage(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDto user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);

        securityService.autoLogin(user.getUsername(), user.getPassword());

        return "redirect:/login";
    }
}
