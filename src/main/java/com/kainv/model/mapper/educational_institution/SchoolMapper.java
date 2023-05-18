package com.kainv.model.mapper.educational_institution;

import com.kainv.model.dto.educational_institution.SchoolDto;
import com.kainv.model.entity.education_institution_domain.School;
import com.kainv.model.mapper.IMapper;
import com.kainv.model.mapper.personal_cabinet.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface SchoolMapper extends IMapper<SchoolDto, School> {
}