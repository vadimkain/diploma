package com.kainv.model.dto.personal_cabinet_schema;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PrivilegeDto {
    Long id;
    String name;
    String description;
}
