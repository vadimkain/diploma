package com.kainv.model.services.mapper.education_institution_schema;

import com.kainv.model.dto.education_institution_schema.CreateSchoolDto;
import com.kainv.model.entities.education_institution_schema.School;
import com.kainv.model.services.mapper.personal_cabinet_schema.RegistrationUserMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = RegistrationUserMapper.class)
public interface CreateSchoolMapper {
    School toEntity(CreateSchoolDto createSchoolDto);

    CreateSchoolDto toDto(School school);
}