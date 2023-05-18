package com.kainv.rest;

import com.kainv.model.dto.AddDirectorDto;
import com.kainv.model.dto.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kainv.util.RequestMappingPathV1.REGISTRATION;

@Slf4j
@RestController
@RequestMapping(REGISTRATION)
public class RegistrationRestControllerV1 {
    @PostMapping("/director")
    public AuthenticationResponse registrationDirector(@RequestBody AddDirectorDto addDirectorDto) {
        return new AuthenticationResponse();
    }
}