package burgerfactory.endpoints.order.service.impl;

import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.factory.PaymentFactory;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.endpoints.order.service.OrderService;
import burgerfactory.endpoints.testdata.TestData;
import burgerfactory.infrastructure.enums.Variables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private static final String PAYPAL_STRATEGY = "PAYPAL";
    private static final String CREDIT_CARD_STRATEGY = "CREDIT_CARD";
    private static final String PAYED = "PAYED";
    private static final String UNKNOWN_STRATEGY = "UNKNOWN_STRATEGY";
    private static final String SUCCESSFUL_REDIRECTION = "redirect:/order/getInvoice";
    private static final String PAYMENT_STRATEGY_FAILURE_REDIRECTION = "redirect:/order/getInvoice";
    private static final String PRODUCT_NOT_SELECTED_FAILURE_REDIRECTION = "redirect:/burgerFactory/main";

    private String currentStrategy = "";

    @Mock
    PaymentFactory paymentFactory;
    @Mock
    RedirectAttributes redirectAttributes;

    OrderService orderService;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderServiceImpl();
    }

    @Test
    public void testCalculateTotalPrice() {
        assertThat(orderService.calculateTotalPrice(TestData.getOrderDto(PAYPAL_STRATEGY)), is(25f));
    }

    @Test
    public void testRemitPaymentPaypalStrategy() {
        assertThat(orderService.remitPayment(TestData.getOrderDto(PAYPAL_STRATEGY), redirectAttributes), is(SUCCESSFUL_REDIRECTION));
    }

    @Test
    public void testRemitPaymentCreditCardStrategy() {
        assertThat(orderService.remitPayment(TestData.getOrderDto(CREDIT_CARD_STRATEGY), redirectAttributes), is(SUCCESSFUL_REDIRECTION));
    }

    @Test
    public void testRemitPaymentFailed() {
        assertThat(orderService.remitPayment(new OrderDto(), redirectAttributes), is(PRODUCT_NOT_SELECTED_FAILURE_REDIRECTION));
    }

    @Test
    public void testRemitPaymentFailedOnUnknownPaymentStrategy() {
        assertThat(orderService.remitPayment(TestData.getOrderDto(UNKNOWN_STRATEGY), redirectAttributes), is(PAYMENT_STRATEGY_FAILURE_REDIRECTION));
    }

    @Test
    public void testGetInvoice() {
        OrderDto invoice = TestData.getOrderDto(PAYPAL_STRATEGY);
        assertThat(invoice.getTotalPrice(), is(0.0f));

        orderService.getInvoice(invoice);
        assertThat(invoice.getTotalPrice(), is(25f));
    }
}