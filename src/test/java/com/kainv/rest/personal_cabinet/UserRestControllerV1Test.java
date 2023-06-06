package com.kainv.rest.personal_cabinet;

import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.dto.personal_cabinet.UserInfoDto;
import com.kainv.model.entity.personal_cabinet_domain.Birthday;
import com.kainv.model.service.personal_cabinet.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserRestControllerV1Test {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserRestControllerV1 userRestControllerV1;

    @Test
    void getUser_WithValidPrincipal_ReturnsUserInfoDto() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate("2003-11-16")
                .email("kain.vadym50@gmail.com")
                .build();

        when(userService.getAuthorizedUserProfileByEmail(anyString())).thenReturn(Optional.of(userInfoDto));

        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.getUser(principal);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(userInfoDto, response.getBody().getContent());

        verify(userService, times(1)).getAuthorizedUserProfileByEmail(eq("kain.vadym50@gmail.com"));
    }

    @Test
    void getUser_WithNullPrincipal_ReturnsUnauthorized() {
        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.getUser(null);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).getAuthorizedUserProfileByEmail(anyString());
    }

    @Test
    void getUser_WithInvalidPrincipal_ReturnsNotFound() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("invalid@example.com");

        when(userService.getAuthorizedUserProfileByEmail(anyString())).thenReturn(Optional.empty());

        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.getUser(principal);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).getAuthorizedUserProfileByEmail(eq("invalid@example.com"));
    }

    @Test
    void updateUser_WithValidPrincipalAndMatchingEmail_ReturnsUpdatedUserInfoDto() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");

        UserDto userDto = UserDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate(new Birthday(LocalDate.of(2003, 11, 16)))
                .email("kain.vadym50@gmail.com")
                .build();

        when(userService.updateEntity(any(UserDto.class))).thenReturn(Optional.of(userDto));

        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.updateUser(userDto, principal);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(userDto.getEmail(), response.getBody().getContent().getEmail());

        verify(userService, times(1)).updateEntity(eq(userDto));
    }

    @Test
    void updateUser_WithNullPrincipal_ReturnsUnauthorized() {
        UserDto userDto = UserDto.builder()
                .id(37L)
                .patronymic("Dmytrovych")
                .email("kain.vadym50@gmail.com")
                .build();

        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.updateUser(userDto, null);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).updateEntity(any(UserDto.class));
    }


    @Test
    void updateUser_WithInvalidPrincipal_ReturnsUnauthorized() {
        UserDto userDto = UserDto.builder()
                .id(37L)
                .patronymic("Dmytrovych")
                .email("kain.vadym50@gmail.com")
                .build();

        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("invalid@example.com");

        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.updateUser(userDto, principal);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).updateEntity(any(UserDto.class));
    }

    @Test
    void updateUser_WithValidPrincipalAndNonMatchingEmail_ReturnsUnauthorized() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");

        UserDto userDto = UserDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate(new Birthday(LocalDate.of(2003, 11, 16)))
                .email("other@example.com")
                .build();

        // When
        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.updateUser(userDto, principal);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).updateEntity(any(UserDto.class));
    }

//    @Test
//    void updateUser_WithValidPrincipalAndMatchingEmailButUpdateFails_ReturnsNotFound() {
//        // Given
//        Principal principal = mock(Principal.class);
//        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");
//
//        UserDto userDto = UserDto.builder()
//                .id(37L)
//                .surname("Kain")
//                .firstName("Vadim")
//                .patronymic("Alex")
//                .birthDate(new Birthday(LocalDate.of(2003, 11, 16)))
//                .email("kain.vadym50@gmail.com")
//                .build();
//
//        when(userService.updateEntity(any(UserDto.class))).thenReturn(Optional.empty());
//
//        // When
//        ResponseEntity<EntityModel<UserInfoDto>> response = userRestControllerV1.updateUser(userDto, principal);
//
//        // Then
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//
//        verify(userService, times(1)).updateEntity(eq(userDto));
//    }

    @Test
    void deleteUser_WithValidPrincipalAndMatchingEmail_ReturnsOk() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate("2003-11-16")
                .email("kain.vadym50@gmail.com")
                .build();

        when(userService.deleteEntity(anyLong())).thenReturn(true);

        // When
        ResponseEntity<?> response = userRestControllerV1.deleteUser(userInfoDto, principal);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());

        verify(userService, times(1)).deleteEntity(eq(37L));
    }

    @Test
    void deleteUser_WithNullPrincipal_ReturnsUnauthorized() {

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate("2003-11-16")
                .email("kain.vadym50@gmail.com")
                .build();

        // When
        ResponseEntity<?> response = userRestControllerV1.deleteUser(userInfoDto, null);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).deleteEntity(anyLong());
    }

    @Test
    void deleteUser_WithInvalidPrincipal_ReturnsUnauthorized() {
        // Given
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate("2003-11-16")
                .email("kain.vadym50@gmail.com")
                .build();

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("invalid@example.com");

        // When
        ResponseEntity<?> response = userRestControllerV1.deleteUser(userInfoDto, principal);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).deleteEntity(anyLong());
    }

    @Test
    void deleteUser_WithValidPrincipalAndNonMatchingEmail_ReturnsUnauthorized() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate("2003-11-16")
                .email("other@example.com")
                .build();

        // When
        ResponseEntity<?> response = userRestControllerV1.deleteUser(userInfoDto, principal);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        verify(userService, never()).deleteEntity(anyLong());
    }

    @Test
    void deleteUser_WithValidPrincipalAndMatchingEmailButDeleteFails_ReturnsNotFound() {
        // Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("kain.vadym50@gmail.com");

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(37L)
                .surname("Kain")
                .firstName("Vadim")
                .patronymic("Alex")
                .birthDate("2003-11-16")
                .email("kain.vadym50@gmail.com")
                .build();

        when(userService.deleteEntity(anyLong())).thenReturn(false);

        // When
        ResponseEntity<?> response = userRestControllerV1.deleteUser(userInfoDto, principal);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).deleteEntity(eq(37L));
    }
}
