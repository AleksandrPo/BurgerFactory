package burgerfactory.endpoints.auth.dao;

import burgerfactory.endpoints.auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
