package burgerfactory.endpoints.app.configuration;

import burgerfactory.endpoints.app.facade.AppFacade;
import burgerfactory.endpoints.app.service.AppService;
import burgerfactory.endpoints.auth.dao.UserDao;
import burgerfactory.endpoints.auth.dao.impl.UserDaoImpl;
import burgerfactory.endpoints.app.facade.impl.AppFacadeImpl;
import burgerfactory.endpoints.app.service.impl.AppServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public AppFacade appFacade() {
        return new AppFacadeImpl();
    }

    @Bean
    public AppService appService() {
        return new AppServiceImpl();
    }

    @Bean
    public UserDao persistence() {
        return new UserDaoImpl();
    }
}
