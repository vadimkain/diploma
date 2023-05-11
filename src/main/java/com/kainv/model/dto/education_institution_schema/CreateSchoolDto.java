package com.kainv.model.dto.education_institution_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.DefaultUserDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateSchoolDto {
    private String name;
    private String description;
    private DefaultUserDto defaultUserDto;
}