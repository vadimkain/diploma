package com.kainv.model.services.mapper.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.PrivilegeDto;
import com.kainv.model.entities.personal_cabinet_schema.Privilege;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PrivilegeMapper {

    PrivilegeDto toDto(Privilege privilege);

    List<PrivilegeDto> toDtoList(List<Privilege> privilegeList);

    Privilege toEntity(PrivilegeDto privilegeDto);

    List<Privilege> toEntityList(List<PrivilegeDto> privilegeDtoList);
}


