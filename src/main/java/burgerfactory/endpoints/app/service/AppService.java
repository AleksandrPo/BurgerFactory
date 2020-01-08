package burgerfactory.endpoints.app.service;

import burgerfactory.endpoints.app.model.Menu;
import burgerfactory.endpoints.order.dto.OrderDto;

public interface AppService {
    Menu getMenu();
    OrderDto getNewOrder(Menu menu);
}
