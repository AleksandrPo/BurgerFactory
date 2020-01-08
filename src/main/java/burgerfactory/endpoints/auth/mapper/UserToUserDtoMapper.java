package burgerfactory.endpoints.auth.mapper;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.infrastructure.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;

public class UserToUserDtoMapper extends AbstractMapper<User, UserDto> {

    public UserToUserDtoMapper() {}
    public UserToUserDtoMapper(ModelMapper modelMapper) {
    }

    public void map(User user, UserDto userDto) {
        getMapper().map(user, userDto);
    }

    @Override
    public UserDto map(User from) {
        return getMapper().map(from, UserDto.class);
    }
}