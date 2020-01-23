package burgerfactory.endpoints.auth.mapper;

import burgerfactory.endpoints.testdata.TestData;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;

public class UserToUserDtoMapperTest {

    private UserToUserDtoMapper userToUserDtoMapper;

    @Before
    public void setUp() throws Exception {
        userToUserDtoMapper = new UserToUserDtoMapper();
    }

    @Test
    public void testMap() {
        userToUserDtoMapper.map(TestData.getUser());
        assertThat(userToUserDtoMapper.map(TestData.getUser()), samePropertyValuesAs(TestData.getUserDto()));
    }
}