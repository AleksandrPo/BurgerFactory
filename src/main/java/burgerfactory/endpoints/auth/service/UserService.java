package burgerfactory.endpoints.auth.service;

import burgerfactory.endpoints.auth.dto.UserDto;

public interface UserService {
    UserDto save(UserDto u);
    UserDto updateUser(UserDto u);
    void deleteUser(UserDto u);
    UserDto findByUsername(String username);
    String getUsername(String username);
}
