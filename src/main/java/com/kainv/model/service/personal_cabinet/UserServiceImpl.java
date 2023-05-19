package com.kainv.model.service.personal_cabinet;

import com.kainv.model.dto.personal_cabinet.AddUserDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.entity.personal_cabinet_domain.User;
import com.kainv.model.mapper.personal_cabinet.AddUserMapper;
import com.kainv.model.mapper.personal_cabinet.RoleMapper;
import com.kainv.model.mapper.personal_cabinet.UserMapper;
import com.kainv.model.repos.UserRepository;
import com.kainv.model.service.ICrudService;
import com.kainv.model.service.IMediatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements ICrudService<UserDto, AddUserDto>, IUserService<UserDto> {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AddUserMapper addUserMapper;

    private final RoleMapper roleMapper;

    private IMediatorService iMediatorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AddUserMapper addUserMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addUserMapper = addUserMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<UserDto> createEntity(AddUserDto addEntityDto) {

        if (userRepository.findByEmail(addEntityDto.getEmail()) != null) {
            log.info("user with email: {} already exists", addEntityDto.getEmail());
            return Optional.empty();
        }

        Optional<User> userDto = Optional.of(userRepository.save(addUserMapper.toUser(addEntityDto)));
        log.info("user with email: {} does not exist and he has added to database", addEntityDto.getEmail());

        return userDto.map(userMapper::toDto);
    }

    @Override
    public Optional<UserDto> updateEntity(UserDto entityDto) {

        Optional<User> userById = userRepository.findById(entityDto.getId());

        Optional<UserDto> updatedUserDtoOptional = userById.map(user -> {
            user.setEmail(entityDto.getEmail());
            user.setPassword(entityDto.getPassword());
            user.setSurname(entityDto.getSurname());
            user.setFirstName(entityDto.getFirstName());
            user.setPatronymic(entityDto.getPatronymic());
            user.setBirthDate(entityDto.getBirthDate());
            user.setRoles(roleMapper.toEntityList(entityDto.getRoles()));

            userRepository.save(user);

            log.info("user user with id {} and email {} has updated", entityDto.getId(), entityDto.getEmail());

            return userMapper.toDto(user);
        });

        log.info("user with id {} and email {} does not exist", entityDto.getId(), entityDto.getEmail());

        return Optional.empty();
    }

    @Override
    public boolean deleteEntity(Long id) {
        Optional<User> userById = userRepository.findById(id);

        userById.map(user -> {
            userRepository.deleteById(user.getId());

            log.info("user with id {} and email {} has deleted", user.getId(), user.getEmail());

            return true;
        });

        log.info("user with id {} has not deleted because he has not found", id);

        return false;
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        Optional<User> userByEmail = Optional.ofNullable(userRepository.findByEmail(email));

        return userByEmail.map(userMapper::toDto);
    }
}
