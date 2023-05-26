package com.kainv.model.dto.personal_cabinet;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserInfoDto {
    private Long id;
    private String surname;
    private String firstName;
    private String patronymic;
    private String birthDate;
    private String email;
    private List<RoleDto> roles;
}
