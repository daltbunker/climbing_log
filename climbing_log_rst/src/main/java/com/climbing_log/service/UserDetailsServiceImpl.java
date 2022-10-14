package com.climbing_log.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.User;
import com.climbing_log.model.UserDetailsImpl;
import com.climbing_log.repository.UserRepository;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        System.out.println(user.get().getPassword());
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        return user.map(UserDetailsImpl::new).get();
    }

    public Integer addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    public User getUserByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username must not be null");
        }
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }

        User user = userOpt.get();
        return user;
    }

}
