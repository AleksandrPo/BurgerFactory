package burgerfactory.endpoints.order.configuration;

import burgerfactory.endpoints.order.facade.impl.OrderFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacadeImpl orderFacade() {
        return new OrderFacadeImpl();
    }
}
