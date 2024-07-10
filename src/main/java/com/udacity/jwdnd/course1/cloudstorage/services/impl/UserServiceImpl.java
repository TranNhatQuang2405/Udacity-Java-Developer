package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.repository.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return user;
    }

    @Override
    public User register(User user) throws Exception {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setRole("USER");
        try {
            userMapper.insertUser(user);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }
}
