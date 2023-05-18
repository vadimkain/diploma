package com.kainv.model.dto.educational_institution;

import com.kainv.model.dto.personal_cabinet.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolDto {
    private Long id;
    private String name;
    private String description;
    private UserDto director;
}
