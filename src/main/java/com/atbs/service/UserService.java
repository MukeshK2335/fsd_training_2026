package com.atbs.service;

import com.atbs.model.User;
import com.atbs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (User) userRepository.findByUsernameAndActiveTrue(username).orElseThrow(()->new UsernameNotFoundException("Invalid Credentials"));



    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
