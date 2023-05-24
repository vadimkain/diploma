package com.kainv.model.service.personal_cabinet;

import com.kainv.exceptions.UserAlreadyExistsException;
import com.kainv.exceptions.UserNotFoundException;
import com.kainv.model.dto.personal_cabinet.AddUserDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.entity.personal_cabinet_domain.User;
import com.kainv.model.mapper.personal_cabinet.AddUserMapper;
import com.kainv.model.mapper.personal_cabinet.RoleMapper;
import com.kainv.model.mapper.personal_cabinet.UserMapper;
import com.kainv.model.repos.UserRepository;
import com.kainv.model.service.ICrudService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements ICrudService<UserDto, AddUserDto> {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AddUserMapper addUserMapper;

    private final RoleMapper roleMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AddUserMapper addUserMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addUserMapper = addUserMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Optional<UserDto> createEntity(AddUserDto addEntityDto) {
        try {
            if (userRepository.findByEmail(addEntityDto.getEmail()).isPresent()) {
                log.info("User with email: {} already exists", addEntityDto.getEmail());
                throw new UserAlreadyExistsException("User with email " + addEntityDto.getEmail() + " already exists");
            }

            addEntityDto.setPassword(passwordEncoder.encode(addEntityDto.getPassword()));

            User user = userRepository.save(addUserMapper.toUser(addEntityDto));
            log.info("User with email: {} does not exist and has been added to the database", addEntityDto.getEmail());

            return Optional.of(user).map(userMapper::toDto);
        } catch (UserAlreadyExistsException e) {
            log.error("Error creating user: {}", e.getMessage());
            return Optional.empty();
        }
    }


    @Transactional
    @Override
    public Optional<UserDto> updateEntity(UserDto entityDto) {
        try {
            Optional<User> userById = userRepository.findById(entityDto.getId());

            if (userById.isPresent()) {
                User user = userById.get();
                user.setEmail(entityDto.getEmail());
                user.setPassword(passwordEncoder.encode(entityDto.getPassword()));
                user.setSurname(entityDto.getSurname());
                user.setFirstName(entityDto.getFirstName());
                user.setPatronymic(entityDto.getPatronymic());
                user.setBirthDate(entityDto.getBirthDate());
                user.setRoles(roleMapper.toEntityList(entityDto.getRoles()));

                userRepository.save(user);

                log.info("User with id {} and email {} has been updated", entityDto.getId(), entityDto.getEmail());

                return Optional.of(userMapper.toDto(user));
            } else {
                log.info("User with id {} does not exist", entityDto.getId());
                throw new UserNotFoundException("User not found");
            }
        } catch (UserNotFoundException e) {
            log.error("Error update user: {}", e.getMessage());
            return Optional.empty();
        }
    }


    @Transactional
    @Override
    public boolean deleteEntity(Long id) {
        try {
            Optional<User> userById = userRepository.findById(id);

            if (userById.isPresent()) {
                User user = userById.get();
                userRepository.deleteById(user.getId());

                log.info("User with id {} and email {} has been deleted", user.getId(), user.getEmail());

                return true;
            } else {
                log.info("User with id {} has not been deleted because it was not found", id);
                throw new UserNotFoundException("User not found");
            }
        } catch (UserNotFoundException e) {
            log.error("Error delete user: {}", e.getMessage());
            return false;
        }
    }

}
