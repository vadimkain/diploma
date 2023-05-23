package com.kainv.model.mapper.personal_cabinet;

import com.kainv.model.dto.personal_cabinet.RoleDto;
import com.kainv.model.entity.personal_cabinet_domain.Role;
import com.kainv.model.mapper.IMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper extends IMapper<RoleDto, Role> {
    List<RoleDto> toDtoList(List<Role> roleList);

    List<Role> toEntityList(List<RoleDto> roleDtoList);
}
