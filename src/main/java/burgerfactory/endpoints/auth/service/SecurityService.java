package burgerfactory.endpoints.auth.service;

import burgerfactory.endpoints.auth.dto.UserDto;

public interface SecurityService {
    void autoLogin(String username, String password);
    void refresh(UserDto userDto);
}
