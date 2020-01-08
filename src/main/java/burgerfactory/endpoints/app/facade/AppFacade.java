package burgerfactory.endpoints.app.facade;

import burgerfactory.endpoints.app.model.Menu;
import burgerfactory.endpoints.order.dto.OrderDto;

public interface AppFacade {
    Menu getMenu();
    OrderDto prepareNewOrder(Menu menu);
}
