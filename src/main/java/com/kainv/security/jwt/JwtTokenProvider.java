package com.kainv.security.jwt;

import com.kainv.model.dto.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class JwtTokenProvider {
    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenProvider(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponse provideToken(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (final BadCredentialsException e) {
            log.warn("user {} has not authenticated successfully (HttpStatus: UNAUTHORIZED)", email);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));

        log.info("user {} has received token successfully", email);

        return authenticationResponse;
    }
}
