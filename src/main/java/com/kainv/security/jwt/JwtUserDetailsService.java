package com.kainv.security.jwt;

import com.kainv.model.entity.personal_cabinet_domain.User;
import com.kainv.model.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        JwtUserDetails jwtUserDetails = JwtUserDetailsFactory.create(user.get());
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);

        return jwtUserDetails;
    }
}
