package com.kainv.model.service;

import com.kainv.exceptions.MyEntityNotFoundException;
import com.kainv.model.dto.AddDirectorDto;
import com.kainv.model.dto.DirectorDto;
import com.kainv.model.dto.educational_institution.SchoolDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.service.educational_institution.SchoolServiceImpl;
import com.kainv.model.service.personal_cabinet.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RegistrationServiceMediatorImpl implements IMediatorService {

    private final UserServiceImpl userService;
    private final SchoolServiceImpl schoolService;

    @Autowired
    public RegistrationServiceMediatorImpl(UserServiceImpl userService, SchoolServiceImpl schoolService) {
        this.userService = userService;
        this.schoolService = schoolService;
    }

    @Transactional
    @Override
    public Optional<DirectorDto> addDirector(AddDirectorDto addDirectorDto) {
        UserDto userDto = userService.createEntity(addDirectorDto.getAddUserDto())
                .orElseThrow(MyEntityNotFoundException::new);

        SchoolDto schoolDto = schoolService.createEntity(addDirectorDto.getAddSchoolDto())
                .orElseThrow(MyEntityNotFoundException::new);

        log.info("Director {} and school {} have been created successfully", userDto.getEmail(), schoolDto.getName());

        return Optional.of(DirectorDto.builder()
                .userDto(userDto)
                .schoolDto(schoolDto)
                .build());
    }
}