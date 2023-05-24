package com.kainv.rest;

import com.kainv.model.dto.AuthenticationResponse;
import com.kainv.model.dto.personal_cabinet.AddUserDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.entity.personal_cabinet_domain.Role;
import com.kainv.model.repos.RoleRepository;
import com.kainv.model.service.personal_cabinet.UserServiceImpl;
import com.kainv.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kainv.util.RequestMappingPathV1.REGISTRATION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(REGISTRATION)
public class RegistrationRestControllerV1 {
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public RegistrationRestControllerV1(RoleRepository roleRepository, UserServiceImpl userService, JwtTokenProvider jwtTokenProvider) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //    TODO: усовершенствовать
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Role>>> getRoles() {
        List<EntityModel<Role>> roles = roleRepository.findAll().stream()
                .map(role -> EntityModel.of(role,
                        linkTo(methodOn(RegistrationRestControllerV1.class).getRoles()).withRel("roles")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Role>> collectionModel = CollectionModel.of(roles,
                linkTo(methodOn(RegistrationRestControllerV1.class).getRoles()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<AuthenticationResponse>> registration(@RequestBody AddUserDto addUserDto) {
        Optional<UserDto> userDto = userService.createEntity(addUserDto);

        if (userDto.isPresent()) {
            AuthenticationResponse authenticationResponse = jwtTokenProvider.provideToken(userDto.get().getEmail(), userDto.get().getPassword());
            return ResponseEntity.ok(
                    EntityModel.of(authenticationResponse).add(
                            linkTo(methodOn(RegistrationRestControllerV1.class).registration(addUserDto)).withSelfRel()
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}