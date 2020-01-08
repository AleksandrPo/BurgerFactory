package burgerfactory.endpoints.app.facade.impl;

import burgerfactory.endpoints.app.facade.AppFacade;
import burgerfactory.endpoints.app.service.impl.AppServiceImpl;
import burgerfactory.endpoints.app.model.Menu;
import burgerfactory.endpoints.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

public class AppFacadeImpl implements AppFacade {

    @Autowired
    private AppServiceImpl appService;

    @Override
    public Menu getMenu() {
        return appService.getMenu();
    }

    @Override
    public OrderDto prepareNewOrder(Menu menu) {
        return appService.getNewOrder(menu);
    }
}
