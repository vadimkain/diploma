package com.kainv.model.services.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import com.kainv.model.dto.personal_cabinet_schema.User.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService implements UserService {
    @Override
    public Optional<UserDto> createUser(RegistrationUserDto registrationUserDto) {
        return Optional.empty();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long id) {

    }
}
