package com.kainv.security;

import com.kainv.model.entities.personal_cabinet_schema.User.User;
import com.kainv.model.services.mapper.personal_cabinet_schema.UserMapper;
import com.kainv.model.services.personal_cabinet_schema.UserService;
import com.kainv.security.jwt.JwtUser;
import com.kainv.security.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .map(userDto -> userMapper.toEntity(userDto))
                .orElseThrow(() -> new UsernameNotFoundException("user with username: " + email + " not found."));

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with email {} successfully loaded", email);

        return jwtUser;
    }
}
