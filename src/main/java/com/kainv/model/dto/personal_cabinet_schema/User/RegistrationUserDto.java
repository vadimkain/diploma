package com.kainv.model.dto.personal_cabinet_schema.User;

import com.kainv.model.dto.personal_cabinet_schema.RoleDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RegistrationUserDto {
    String surname;
    String firstName;
    String patronymic;
    String birthDate;
    String email;
    String password;
    List<RoleDto> roles;
}
