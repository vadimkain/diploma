package com.kainv.model.dto;

import com.kainv.model.dto.educational_institution.SchoolDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirectorDto {
    private UserDto userDto;
    private SchoolDto schoolDto;
}
