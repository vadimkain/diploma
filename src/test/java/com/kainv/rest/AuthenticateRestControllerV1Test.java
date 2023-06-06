package com.kainv.rest;

import com.kainv.model.dto.AuthenticationRequest;
import com.kainv.model.dto.AuthenticationResponse;
import com.kainv.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticateRestControllerV1Test {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthenticateRestControllerV1 authenticateRestController;

    @Test
    public void getToken_ReturnsToken_Successfully() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken("access-token");

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(email);
        authenticationRequest.setPassword(password);

        when(jwtTokenProvider.provideToken(email, password)).thenReturn(authenticationResponse);

        // Act
        ResponseEntity<EntityModel<AuthenticationResponse>> response = authenticateRestController.getToken(authenticationRequest);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getContent().getAccessToken()).isEqualTo(authenticationResponse.getAccessToken());
        // Add more assertions if necessary
    }

    @Test
    public void getToken_ReturnsBadRequest_WhenAuthenticationRequestIsNull() {
        // Act
        ResponseEntity<EntityModel<AuthenticationResponse>> response = authenticateRestController.getToken(null);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        // Add more assertions if necessary
    }

    // Add more tests for edge cases and error scenarios if necessary
}
