package burgerfactory.integration;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.facade.OrderFacade;
import burgerfactory.endpoints.testdata.TestData;
import burgerfactory.infrastructure.enums.Messages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderEndpointTest {

    OrderDto orderDto;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private OrderFacade orderFacade;

    @Before
    public void setUp() throws Exception {
        orderDto = TestData.getOrderDto("PAYPAL");
    }

    @WithMockUser("username")
    @Test
    public void testOrderEndpoint() throws Exception {

        mockMvc.perform(get("/order/getInvoice")
                .flashAttr("invoice", orderDto))
                .andExpect(status().isOk())
                .andExpect(view().name("/orderPage"));

        mockMvc.perform(post("/order/approveAndPay")
                .flashAttr("invoice", orderDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/burgerFactory/main"));

        orderDto.getPaymentStrategy().setStrategy("CREDIT_CARD");

        mockMvc.perform(post("/order/approveAndPay")
                .flashAttr("invoice", orderDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/order/getInvoice"));

        orderDto = TestData.getOrderDto("CREDIT_CARD");

        mockMvc.perform(post("/order/approveAndPay")
                .flashAttr("invoice", orderDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/burgerFactory/main"));
    }
}