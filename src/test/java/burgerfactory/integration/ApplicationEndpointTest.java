package burgerfactory.integration;

import burgerfactory.endpoints.app.facade.AppFacade;
import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.service.UserService;
import burgerfactory.endpoints.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationEndpointTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AppFacade appFacade;
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    @WithMockUser("username")
    @Test
    public void testApplicationEndpoint() throws Exception {
        mockMvc.perform(get("/burgerFactory/main"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("paymentSuccessfulMessage", ""))
                .andExpect(model().attribute("menu", hasProperty("burgersMenu", hasSize(3))))
                .andExpect(model().attribute("menu", hasProperty("potatoFreesMenu", hasSize(3))))
                .andExpect(model().attribute("menu", hasProperty("burgersMenu", hasSize(3))))

                .andExpect(model().attribute("order", hasProperty("burgers", hasSize(3))))
                .andExpect(model().attribute("order", hasProperty("drinks", hasSize(3))))
                .andExpect(model().attribute("order", hasProperty("potatoFrees", hasSize(3))))
                .andExpect(model().attribute("order", hasProperty("paymentStrategy", hasProperty("strategy", is("PAYPAL")))));

        mockMvc.perform(post("/burgerFactory/calculateTotalPrice").flashAttr("order", appFacade.prepareNewOrder(TestData.getMenu())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/order/getInvoice"));

        mockMvc.perform(get("/burgerFactory/userInfo"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userInfo", hasProperty("username", is("username"))))
                .andExpect(model().attribute("userInfo", hasProperty("email", is("user@user.lv"))))
                .andExpect(model().attribute("userInfo", hasProperty("phone", is(78904562))));

        mockMvc.perform(post("/burgerFactory/userInfo").flashAttr("userInfo", getNewUserInfo("newUsername", "newEmail", 1234567, "password")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userInfo", hasProperty("username", is("newUsername"))))
                .andExpect(model().attribute("userInfo", hasProperty("email", is("newEmail"))))
                .andExpect(model().attribute("userInfo", hasProperty("phone", is(1234567))));
    }

    private UserDto getNewUserInfo(String username, String email, Integer phone, String password) {
        UserDto userInfo = new UserDto();
        userInfo.setUsername(username);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfo.setPassword(password);
        return userInfo;
    }
}