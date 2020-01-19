package burgerfactory.endpoints.auth.dao;

import burgerfactory.endpoints.auth.model.User;
import org.springframework.stereotype.Repository;

public interface UserDao {
    User findUserByUsername(String username);
}
