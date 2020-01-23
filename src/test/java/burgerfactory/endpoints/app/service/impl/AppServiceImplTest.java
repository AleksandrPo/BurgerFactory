package burgerfactory.endpoints.app.service.impl;

import burgerfactory.endpoints.app.dao.AppDao;
import burgerfactory.endpoints.order.dto.OrderDto;
import burgerfactory.endpoints.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppServiceImplTest {

    @Mock
    AppDao appDao;

    @InjectMocks
    AppServiceImpl appService;

    @Before
    public void setUp() throws Exception {
        when(appDao.findById(anyShort())).thenReturn(Optional.of(TestData.getMenu()));
    }

    @Test
    public void testGetMenu() {
        appService.getMenu();
        verify(appDao).findById(anyShort());
    }

    @Test
    public void testGetNewOrder() {
        OrderDto newOrder = appService.getNewOrder(TestData.getMenu());
        assertThat(newOrder.getBurgers().size(), is(1));
        assertThat(newOrder.getDrinks().size(), is(2));
        assertThat(newOrder.getPotatoFrees().size(), is(3));
    }
}