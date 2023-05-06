package com.kainv.model.services.mapper.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.RoleDto;
import com.kainv.model.entities.personal_cabinet_schema.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {PrivilegeMapper.class})
public interface RoleMapper {

    RoleDto toDto(Role role);

    List<RoleDto> toDtoList(List<Role> roleList);

    Role toEntity(RoleDto roleDto);

    List<Role> toEntityList(List<RoleDto> roleDtoList);
}


