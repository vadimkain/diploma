package com.kainv.model.service.personal_cabinet;

import com.kainv.exceptions.UserAlreadyExistsException;
import com.kainv.exceptions.UserNotFoundException;
import com.kainv.model.dto.personal_cabinet.AddUserDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.dto.personal_cabinet.UserInfoDto;
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

    public Optional<UserInfoDto> getAuthorizedUserProfileByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(usr -> UserInfoDto.builder()
                .id(usr.getId())
                .surname(usr.getSurname())
                .firstName(usr.getFirstName())
                .patronymic(usr.getPatronymic())
                .email(usr.getEmail())
                .birthDate(usr.getBirthDate().getBirthDate().toString())
                .roles(roleMapper.toDtoList(usr.getRoles()))
                .build()
        );
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
                boolean isModified = false;

                if (!user.getEmail().equals(entityDto.getEmail())) {
                    user.setEmail(entityDto.getEmail());
                    isModified = true;
                }
                if (!entityDto.getPassword().isEmpty()) {
                    user.setPassword(passwordEncoder.encode(entityDto.getPassword()));
                    isModified = true;
                }
                if (!user.getSurname().equals(entityDto.getSurname())) {
                    user.setSurname(entityDto.getSurname());
                    isModified = true;
                }
                if (!user.getFirstName().equals(entityDto.getFirstName())) {
                    user.setFirstName(entityDto.getFirstName());
                    isModified = true;
                }
                if (!user.getPatronymic().equals(entityDto.getPatronymic())) {
                    user.setPatronymic(entityDto.getPatronymic());
                    isModified = true;
                }
                if (user.getBirthDate() == null || !user.getBirthDate().equals(entityDto.getBirthDate())) {
                    user.setBirthDate(entityDto.getBirthDate());
                    isModified = true;
                }
                if (!user.getRoles().equals(roleMapper.toEntityList(entityDto.getRoles()))) {
                    user.setRoles(roleMapper.toEntityList(entityDto.getRoles()));
                    isModified = true;
                }

                if (isModified) {
                    userRepository.save(user);
                    log.info("User with id {} and email {} has been updated", entityDto.getId(), entityDto.getEmail());
                } else {
                    log.info("No changes detected for user with id {}", entityDto.getId());
                }

                return Optional.of(userMapper.toDto(user));
            } else {
                log.info("User with id {} does not exist", entityDto.getId());
                throw new UserNotFoundException("User not found");
            }
        } catch (UserNotFoundException e) {
            log.error("Error updating user: {}", e.getMessage());
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
