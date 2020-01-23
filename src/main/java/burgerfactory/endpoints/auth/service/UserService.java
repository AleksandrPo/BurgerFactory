package burgerfactory.endpoints.auth.service;

import burgerfactory.endpoints.auth.dto.UserDto;

import java.security.Principal;

public interface UserService {
    void save(UserDto u);
    UserDto updateUser(UserDto u, Principal principal);
    UserDto findByUsername(String username);
}
