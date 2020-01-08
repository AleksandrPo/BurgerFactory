package burgerfactory.endpoints.auth.service.impl;

import burgerfactory.endpoints.auth.dao.UserDao;
import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.mapper.UserDtoToUserMapper;
import burgerfactory.endpoints.auth.mapper.UserToUserDtoMapper;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.endpoints.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDtoToUserMapper userDtoToUserMapper;
    @Autowired
    private UserToUserDtoMapper userToUserDtoMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User u = userDao.save(userDtoToUserMapper.map(userDto));
        return userToUserDtoMapper.map(u);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User u = userDao.save(userDtoToUserMapper.map(userDto));
        return userToUserDtoMapper.map(u);
    }

    @Override
    public void deleteUser(UserDto u) {
        userDao.delete(userDtoToUserMapper.map(u));
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user == null ? null : userToUserDtoMapper.map(user);
    }

    @Override
    public String getUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user.getUsername();
    }
}
