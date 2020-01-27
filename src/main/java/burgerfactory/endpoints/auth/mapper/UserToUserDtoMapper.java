package burgerfactory.endpoints.auth.mapper;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.infrastructure.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoMapper extends AbstractMapper<User, UserDto> {

    @Override
    public UserDto map(User from) {
        return getMapper().map(from, UserDto.class);
    }
}