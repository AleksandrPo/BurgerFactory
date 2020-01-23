package burgerfactory.endpoints.auth.service.impl;

import burgerfactory.endpoints.auth.dao.impl.UserDaoImpl;
import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.mapper.UserDtoToUserMapper;
import burgerfactory.endpoints.auth.mapper.UserToUserDtoMapper;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.endpoints.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDtoToUserMapper userDtoToUserMapper;
    @Autowired
    private UserToUserDtoMapper userToUserDtoMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public void save(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDaoImpl.save(userDtoToUserMapper.map(userDto));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Principal principal) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User u = userDaoImpl.findUserByUsername(principal.getName());
        userDtoToUserMapper.map(userDto, u);
        return userToUserDtoMapper.map(userDaoImpl.updateEntity(u));
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userDaoImpl.findUserByUsername(username);
        return user == null ? null : userToUserDtoMapper.map(user);
    }
}
