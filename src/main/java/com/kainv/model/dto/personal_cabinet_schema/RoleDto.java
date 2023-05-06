package com.kainv.model.dto.personal_cabinet_schema;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RoleDto {
    Long id;
    String name;
    String description;
    List<PrivilegeDto> privilegeDtoList;
}
