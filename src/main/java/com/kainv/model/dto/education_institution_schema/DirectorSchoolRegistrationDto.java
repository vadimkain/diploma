package com.kainv.model.dto.education_institution_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DirectorSchoolRegistrationDto {
    private RegistrationUserDto registrationUserDto;
    private CreateSchoolDto createSchoolDto;
}
