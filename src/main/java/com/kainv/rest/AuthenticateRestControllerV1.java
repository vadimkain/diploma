package com.kainv.rest;

import com.kainv.model.dto.AuthenticationRequest;
import com.kainv.model.dto.AuthenticationResponse;
import com.kainv.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kainv.util.RequestMappingPathV1.AUTHENTICATE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(AUTHENTICATE)
public class AuthenticateRestControllerV1 {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticateRestControllerV1(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ResponseEntity<EntityModel<AuthenticationResponse>> getToken(@RequestBody final AuthenticationRequest authenticationRequest) {

        if (authenticationRequest == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                EntityModel.of(
                        jwtTokenProvider.provideToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
                ).add(
                        linkTo(methodOn(AuthenticateRestControllerV1.class).getToken(authenticationRequest)).withSelfRel()
                )
        );
    }
}
