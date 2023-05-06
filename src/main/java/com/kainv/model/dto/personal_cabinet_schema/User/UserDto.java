package com.kainv.model.dto.personal_cabinet_schema.User;

import com.kainv.model.dto.personal_cabinet_schema.RoleDto;
import com.kainv.model.entities.personal_cabinet_schema.User.Birthday;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserDto {
    Long id;
    String surname;
    String firstName;
    String patronymic;
    Birthday birthDate;
    String email;
    String password;
    List<RoleDto> roles;
}
