package burgerfactory.infrastructure.configuration;

import burgerfactory.infrastructure.validation.impl.ValidationImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    public ValidationImpl validation() {
        return new ValidationImpl();
    }
}
