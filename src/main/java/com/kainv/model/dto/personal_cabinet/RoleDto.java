package com.kainv.model.dto.personal_cabinet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private String description;
}
