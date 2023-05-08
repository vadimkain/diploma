package com.kainv.model.services.education_institution_schema;

import com.kainv.model.dto.education_institution_schema.CreateSchoolDto;
import com.kainv.model.dto.education_institution_schema.SchoolDto;
import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import com.kainv.model.dto.personal_cabinet_schema.User.UserDto;
import com.kainv.model.services.personal_cabinet_schema.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorSchoolServiceDecorator extends BaseSchoolServiceDecorator {

    @Autowired
    DirectorService directorService;

    public static class DirectorSchoolDto {
        UserDto userDto;
        SchoolDto schoolDto;
    }

    public Optional<DirectorSchoolDto> createEntity(RegistrationUserDto registrationUserDto, CreateSchoolDto createSchoolDto) {
        directorService.createEntity(registrationUserDto);
        createEntity(createSchoolDto);

        return Optional.empty();
    }
}