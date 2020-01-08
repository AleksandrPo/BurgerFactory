package burgerfactory.endpoints.app.dao;

import burgerfactory.endpoints.app.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface AppDao extends CrudRepository<Menu, Short> {
}
