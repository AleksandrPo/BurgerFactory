package burgerfactory.endpoints.app.configuration;

import burgerfactory.endpoints.app.facade.AppFacade;
import burgerfactory.endpoints.app.facade.impl.AppFacadeImpl;
import burgerfactory.endpoints.app.service.AppService;
import burgerfactory.endpoints.app.service.impl.AppServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public AppFacadeImpl appFacade() {
        return new AppFacadeImpl();
    }

    @Bean
    public AppServiceImpl appService() {
        return new AppServiceImpl();
    }
}
