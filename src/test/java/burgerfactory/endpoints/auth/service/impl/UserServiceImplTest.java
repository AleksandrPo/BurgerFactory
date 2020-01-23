package burgerfactory.endpoints.auth.service.impl;

import burgerfactory.endpoints.auth.dao.UserDao;
import burgerfactory.endpoints.auth.dao.impl.UserDaoImpl;
import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.mapper.UserDtoToUserMapper;
import burgerfactory.endpoints.auth.mapper.UserToUserDtoMapper;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.endpoints.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserDtoToUserMapper userDtoToUserMapper;
    @Mock
    UserToUserDtoMapper userToUserDtoMapper;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    UserDaoImpl userDaoImpl;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {

        when(userDtoToUserMapper.map(any(UserDto.class))).thenReturn(TestData.getUser());
        doNothing().when(userDtoToUserMapper).map(any(UserDto.class), any(User.class));
        when(userToUserDtoMapper.map(any(User.class))).thenReturn(TestData.getUserDto());
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encoded password");

        doNothing().when(userDaoImpl).save(any(User.class));
        when(userDaoImpl.findUserByUsername(anyString())).thenReturn(TestData.getUser());
        when(userDaoImpl.updateEntity(any(User.class))).thenReturn(TestData.getUser());
    }

    @Test
    public void testSave() {
        userService.save(TestData.getUserDto());
        verify(userDaoImpl).save(any(User.class));
    }

    @Test
    public void testUpdateUser() {
        UserDto actual = userService.updateUser(TestData.getUserDto(), TestData.getPrincipal("principalName"));

        verify(userDaoImpl).findUserByUsername(anyString());
        verify(userDaoImpl).updateEntity(any(User.class));
        assertThat(actual, samePropertyValuesAs(TestData.getUserDto()));
    }

    @Test
    public void testFindByUsername() {
        userService.findByUsername("username");

        verify(userDaoImpl).findUserByUsername(anyString());
    }
}