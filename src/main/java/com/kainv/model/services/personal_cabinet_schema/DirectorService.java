package com.kainv.model.services.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import com.kainv.model.dto.personal_cabinet_schema.User.UserDto;
import com.kainv.model.entities.personal_cabinet_schema.User.User;
import com.kainv.model.repos.personal_cabinet_schema.UserRepository;
import com.kainv.model.services.CrudService;
import com.kainv.model.services.education_institution_schema.SchoolService;
import com.kainv.model.services.mapper.personal_cabinet_schema.RegistrationUserMapper;
import com.kainv.model.services.mapper.personal_cabinet_schema.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService implements CrudService<UserDto, RegistrationUserDto, User> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationUserMapper registrationUserMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SchoolService schoolService;

    @Override
    public Optional<UserDto> createEntity(RegistrationUserDto registrationUserDto) {
//        step 1 : validation

//        step 2 : map
        User userEntity = registrationUserMapper.toUser(registrationUserDto);

//        step 3 : check at exists
        if (
                userRepository.findBySurnameFirstnamePatronymic(
                        userEntity.getSurname(),
                        userEntity.getFirstName(),
                        userEntity.getPatronymic())
                != null
        ) {
            return Optional.empty();
        }

//        step 4 : insert
        Optional<User> resultInsertUser = Optional.of(userRepository.save(userEntity));

//        step 4 : return
        return Optional.ofNullable(userMapper.toDto(resultInsertUser.get()));
    }

    @Override
    public List<UserDto> getAllEntities() {
        return null;
    }

    @Override
    public Optional<UserDto> getEntityById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> updateEntity(Long id, UserDto EntityDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteEntity(Long id) {
        return false;
    }
}
