package burgerfactory.endpoints.auth.mapper;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.infrastructure.mapper.AbstractMapper;

public class UserDtoToUserMapper extends AbstractMapper<UserDto, User> {

    public void map(UserDto userDto, User user) {
        getMapper().map(userDto, user);
    }

    @Override
    public User map(UserDto userDto) {
        return getMapper().map(userDto, User.class);
    }
}
