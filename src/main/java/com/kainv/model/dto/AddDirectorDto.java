package com.kainv.model.dto;

import com.kainv.model.dto.educational_institution.AddSchoolDto;
import com.kainv.model.dto.personal_cabinet.AddUserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddDirectorDto {
    private AddUserDto addUserDto;
    private AddSchoolDto addSchoolDto;
}
