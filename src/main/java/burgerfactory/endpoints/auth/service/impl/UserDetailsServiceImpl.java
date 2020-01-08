package burgerfactory.endpoints.auth.service.impl;

import burgerfactory.endpoints.auth.dao.UserDao;
import burgerfactory.endpoints.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        if(user == null) { throw new UsernameNotFoundException(username); }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new HashSet<>());
    }
}
