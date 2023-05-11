package com.kainv.model.dto.education_institution_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.DefaultUserDto;

public class SchoolDto {
    private Long id;
    private String name;
    private String description;
    private DefaultUserDto director;
}
