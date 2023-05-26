package com.kainv.rest.personal_cabinet;

import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.dto.personal_cabinet.UserInfoDto;
import com.kainv.model.service.personal_cabinet.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

import static com.kainv.util.RequestMappingPathV1.USER;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(USER)
public class UserRestControllerV1 {

    private final UserServiceImpl userService;

    @Autowired
    public UserRestControllerV1(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<EntityModel<UserInfoDto>> getUser(Principal principal) {
        if (principal != null) {
            Optional<UserInfoDto> authorizedUserProfileByEmail = userService.getAuthorizedUserProfileByEmail(principal.getName());

            return authorizedUserProfileByEmail.map(userInfoDto ->
                    ResponseEntity.ok(EntityModel.of(
                            userInfoDto
                    ).add(
                            linkTo(methodOn(UserRestControllerV1.class).getUser(principal)).withSelfRel()
                    ))
            ).orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping
    public ResponseEntity<EntityModel<UserInfoDto>> updateUser(@RequestBody UserDto userDto, Principal principal) {
        if (principal != null && principal.getName().equals(userDto.getEmail())) {
            Optional<UserDto> userUpdate = userService.updateEntity(userDto);

            if (userUpdate.isPresent()) {
                UserInfoDto userInfoDto = UserInfoDto.builder()
                        .id(userUpdate.get().getId())
                        .surname(userUpdate.get().getSurname())
                        .firstName(userUpdate.get().getFirstName())
                        .patronymic(userUpdate.get().getPatronymic())
                        .email(userUpdate.get().getEmail())
                        .birthDate(userUpdate.get().getBirthDate().getBirthDate().toString())
                        .roles(userUpdate.get().getRoles())
                        .build();

                return ResponseEntity.ok(EntityModel.of(
                        userInfoDto
                ).add(
                        linkTo(methodOn(UserRestControllerV1.class).getUser(principal)).withSelfRel(),
                        linkTo(methodOn(UserRestControllerV1.class).updateUser(userDto, principal)).withSelfRel(),
                        linkTo(methodOn(UserRestControllerV1.class).deleteUser(userInfoDto, principal)).withSelfRel()
                ));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody UserInfoDto userInfoDto, Principal principal) {
        if (principal != null && principal.getName().equals(userInfoDto.getEmail())) {
            boolean isDeleted = userService.deleteEntity(userInfoDto.getId());

            return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}