package burgerfactory.endpoints.auth.dao;

import burgerfactory.endpoints.auth.model.User;

public interface UserDao {
    User findUserByUsername(String username);
}
