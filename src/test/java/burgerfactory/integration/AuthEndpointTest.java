package burgerfactory.integration;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.service.SecurityService;
import burgerfactory.endpoints.auth.service.UserService;
import burgerfactory.endpoints.auth.validation.UserValidator;
import burgerfactory.endpoints.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthEndpointTest {

    private UserDto userDto;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SecurityService securityService;

    @Before
    public void setUp() throws Exception {
        userDto = TestData.getUserDto();
    }

    @Test
    public void testAuthEndpoint() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "username")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/burgerFactory/main"));

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));

        mockMvc.perform(post("/registration").flashAttr("userForm", userDto))
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrors("userForm", "username"))
                .andExpect(model().attributeHasFieldErrors("userForm", "confirmPassword"));

        updateUserDto(null, "username2020", "password");

        mockMvc.perform(post("/registration").flashAttr("userForm", userDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(post("/login")
                .param("username", "username2020")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/burgerFactory/main"));
    }

    private void updateUserDto(Long userId, String username, String confPassword) {
        userDto.setUserId(userId);
        userDto.setUsername(username);
        userDto.setConfirmPassword(confPassword);
    }
}