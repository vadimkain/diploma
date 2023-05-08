package com.kainv.model.services.mapper.education_institution_schema;

import com.kainv.model.dto.education_institution_schema.SchoolDto;
import com.kainv.model.entities.education_institution_schema.School;
import com.kainv.model.services.mapper.personal_cabinet_schema.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface SchoolMapper {
    School toEntity(SchoolDto schoolDto);

    SchoolDto toDto(School school);
}