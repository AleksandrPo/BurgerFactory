package burgerfactory.endpoints.order.factory.impl;

import burgerfactory.endpoints.order.factory.PaymentFactory;
import burgerfactory.endpoints.order.payment.strategy.PaymentStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.CreditCardStrategy;
import burgerfactory.endpoints.order.payment.strategy.impl.PaypalStrategy;
import burgerfactory.endpoints.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentFactoryImplTest {

    private static final String PAYPAL_STRATEGY = "PAYPAL";
    private static final String CREDIT_CARD_STRATEGY = "CREDIT_CARD";

    PaymentFactory paymentFactory;

    @Mock
    RedirectAttributes redirectAttributes;

    @Before
    public void setUp() throws Exception {
        paymentFactory = new PaymentFactoryImpl();
    }

    @Test
    public void testGetPaymentStrategyForPaypalStrategy() {
        PaymentStrategy paymentStrategy = paymentFactory.getPaymentStrategy(PAYPAL_STRATEGY, TestData.getOrderDto(PAYPAL_STRATEGY), redirectAttributes);
        assertThat(paymentStrategy, instanceOf(PaypalStrategy.class));
    }

    @Test
    public void testGetPaymentStrategyForCreditCardStrategy() {
        PaymentStrategy paymentStrategy = paymentFactory.getPaymentStrategy(CREDIT_CARD_STRATEGY, TestData.getOrderDto(CREDIT_CARD_STRATEGY), redirectAttributes);
        assertThat(paymentStrategy, instanceOf(CreditCardStrategy.class));
    }

    @Test
    public void testGetPaymentStrategyForUnknownStrategy() {
        PaymentStrategy paymentStrategy = paymentFactory.getPaymentStrategy("", TestData.getOrderDto(""), redirectAttributes);
        assertThat(paymentStrategy, is(nullValue()));
    }
}