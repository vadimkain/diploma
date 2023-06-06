package com.kainv.rest;

import com.kainv.model.dto.AuthenticationResponse;
import com.kainv.model.dto.personal_cabinet.AddUserDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.entity.personal_cabinet_domain.Role;
import com.kainv.model.repos.RoleRepository;
import com.kainv.model.service.personal_cabinet.UserServiceImpl;
import com.kainv.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RegistrationRestControllerV1Test {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationRestControllerV1 registrationRestController;

    @Test
    public void getRoles_ReturnsRoles_Successfully() {
        // Arrange
        Role role1 = new Role(1L, "Role 1", "description");
        Role role2 = new Role(2L, "Role 2", "description");
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);

        when(roleRepository.findAll()).thenReturn(roles);

        // Act
        ResponseEntity<CollectionModel<EntityModel<Role>>> response = registrationRestController.getRoles();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSize(2);
        // Add more assertions if necessary
    }

    @Test
    public void registration_ReturnsToken_Successfully() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        AddUserDto addUserDto = AddUserDto.builder()
                .email(email)
                .password(password)
                .build();

        UserDto userDto = UserDto.builder()
                .email(email)
                .password(password)
                .build();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken("access-token");

        when(userService.createEntity(addUserDto)).thenReturn(Optional.of(userDto));
        when(jwtTokenProvider.provideToken(email, password)).thenReturn(authenticationResponse);

        // Act
        ResponseEntity<EntityModel<AuthenticationResponse>> response = registrationRestController.registration(addUserDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isEqualTo(authenticationResponse);
        // Add more assertions if necessary
    }

    @Test
    public void registration_ReturnsConflict_WhenUserCreationFails() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        AddUserDto addUserDto = AddUserDto.builder()
                .email(email)
                .password(password)
                .build();

        when(userService.createEntity(addUserDto)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<EntityModel<AuthenticationResponse>> response = registrationRestController.registration(addUserDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        // Add more assertions if necessary
    }

    // Add more tests for edge cases and error scenarios if necessary
}
