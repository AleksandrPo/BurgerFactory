package burgerfactory.endpoints.auth.mapper;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.endpoints.testdata.TestData;
import burgerfactory.infrastructure.mapper.AbstractMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class UserDtoToUserMapperTest {

    private UserDtoToUserMapper userDtoToUserMapper;

    @Before
    public void setUp() throws Exception {
        userDtoToUserMapper = new UserDtoToUserMapper();
    }

    @Test
    public void testMapWithOneArgument() {
        assertThat(userDtoToUserMapper.map(TestData.getUserDto()), samePropertyValuesAs(TestData.getUser()));
    }

    @Test
    public void testMapWithTwoArguments() {
        User u = new User();
        userDtoToUserMapper.map(TestData.getUserDto(), u);
        assertThat(TestData.getUser(), samePropertyValuesAs(u));
    }
}