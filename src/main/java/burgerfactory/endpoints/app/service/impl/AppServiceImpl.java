package burgerfactory.endpoints.app.service.impl;

import burgerfactory.endpoints.app.dao.AppDao;
import burgerfactory.endpoints.app.service.AppService;
import burgerfactory.endpoints.app.model.Menu;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.order.dto.StrategyDto;
import org.springframework.beans.factory.annotation.Autowired;

public class AppServiceImpl implements AppService {

    @Autowired
    AppDao appDao;

    @Override
    public Menu getMenu() {
        return appDao.findById((short)0).orElse(new Menu());
    }

    @Override
    public OrderDto getNewOrder(Menu menu) {
        OrderDto order = new OrderDto();
        if (menu != null) {
            order.setBurgers(menu.getBurgersMenu());
            order.setDrinks(menu.getDrinksMenu());
            order.setPotatoFrees(menu.getPotatoFreesMenu());
            order.setPaymentStrategy(new StrategyDto());
            order.getPaymentStrategy().setStrategy("PAYPAL");
        }
        return order;
    }
}
