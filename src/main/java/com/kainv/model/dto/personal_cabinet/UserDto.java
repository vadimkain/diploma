package com.kainv.model.dto.personal_cabinet;

import com.kainv.model.entity.personal_cabinet_domain.Birthday;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String surname;
    private String firstName;
    private String patronymic;
    private Birthday birthDate;
    private String email;
    private String password;
    private List<RoleDto> roles;
}
