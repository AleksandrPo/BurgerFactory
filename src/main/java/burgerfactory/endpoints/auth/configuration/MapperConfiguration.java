package burgerfactory.endpoints.auth.configuration;

import burgerfactory.endpoints.auth.mapper.UserDtoToUserMapper;
import burgerfactory.endpoints.auth.mapper.UserToUserDtoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public UserDtoToUserMapper userDtoToUserMapper() {
        return new UserDtoToUserMapper();
    }

    @Bean
    public UserToUserDtoMapper userToUserDtoMapper() {
        return new UserToUserDtoMapper();
    }

    }
