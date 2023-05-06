package com.kainv.model.services.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import com.kainv.model.dto.personal_cabinet_schema.User.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> createUser(RegistrationUserDto registrationUserDto);

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(Long id);

    Optional<UserDto> updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
