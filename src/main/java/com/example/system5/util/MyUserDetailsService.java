package com.example.system5.util;

import com.example.system5.model.User;
import com.example.system5.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> authUser = userRepository.findByLogin(login);
        if (authUser.isPresent() && authUser.orElse(null).getDeleted()){
            authUser = Optional.empty();
        }
        return new AuthUser(authUser.orElseThrow(
                () -> new UsernameNotFoundException("User '" + login + "' was not found")));
    }
}