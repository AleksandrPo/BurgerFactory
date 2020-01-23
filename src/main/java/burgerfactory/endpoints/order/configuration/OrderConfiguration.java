package burgerfactory.endpoints.order.configuration;

import burgerfactory.endpoints.order.facade.OrderFacade;
import burgerfactory.endpoints.order.facade.impl.OrderFacadeImpl;
import burgerfactory.endpoints.order.factory.PaymentFactory;
import burgerfactory.endpoints.order.factory.impl.PaymentFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade() {
        return new OrderFacadeImpl();
    }

    @Bean
    public PaymentFactory paymentFactory() {
        return new PaymentFactoryImpl();
    }
}
