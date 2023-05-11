package com.kainv.controller.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.DefaultUserDto;
import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import com.kainv.model.services.education_institution_schema.SchoolService;
import com.kainv.model.services.personal_cabinet_schema.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    SchoolService schoolService;

    @GetMapping("")
    public String hello() {
        return "Hello. This is registration page";
    }

//    @PostMapping
//    public ResponseEntity<?> registrationDirector(@RequestBody DirectorSchoolRegistrationDto directorSchoolRegistrationDto) {
//        Optional<UserDto> userDto = directorService.createEntity(directorSchoolRegistrationDto.getRegistrationUserDto());
//
//        if (userDto.isPresent()) {
//            CreateSchoolDto createSchoolDtoBuild = CreateSchoolDto.builder()
//                    .name(directorSchoolRegistrationDto.getCreateSchoolDto().getName())
//                    .description(directorSchoolRegistrationDto.getCreateSchoolDto().getDescription())
//                    .userDto(userDto.get())
//                    .build();
//
//            schoolService.createEntity(createSchoolDtoBuild);
//
//            return ResponseEntity.ok().build();
//        } else {
//            return new ResponseEntity<>("Не вдалося створити директора разом зі школою", HttpStatus.BAD_REQUEST);
//
//        }
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DefaultUserDto getAsk(@RequestBody RegistrationUserDto registrationUserDto) {
        System.out.println("Success");
        return DefaultUserDto.builder()
                .id(999L)
                .surname(registrationUserDto.getSurname())
                .firstName(registrationUserDto.getFirstName())
                .patronymic(registrationUserDto.getPatronymic())
                .build();
    }
}
