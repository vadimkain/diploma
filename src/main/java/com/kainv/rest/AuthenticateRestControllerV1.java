package com.kainv.rest;

import com.kainv.model.dto.AuthenticationRequest;
import com.kainv.model.dto.AuthenticationResponse;
import com.kainv.security.jwt.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.kainv.util.RequestMappingPathV1.AUTHENTICATE;

@Slf4j
@RestController
@RequestMapping(AUTHENTICATE)
public class AuthenticateRestControllerV1 {
    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticateRestControllerV1(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public AuthenticationResponse getToken(@RequestBody final AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException e) {
            log.warn("user {} has not authenticated successfully (HttpStatus: UNAUTHORIZED)", authenticationRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));

        log.info("user {} has received token successfully", authenticationRequest.getEmail());

        return authenticationResponse;
    }
}
