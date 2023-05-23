package com.kainv.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SecurityConfigTest {
    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    void testAuthentication() {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken("johndoe@example.com", "password1");
        Authentication auth = authenticationManager.authenticate(authToken);
        assertNotNull(auth);
        assertTrue(auth.isAuthenticated());
    }
}