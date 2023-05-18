package com.kainv.model.service;

import com.kainv.model.dto.AddDirectorDto;
import com.kainv.model.dto.DirectorDto;
import com.kainv.model.dto.educational_institution.SchoolDto;
import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.service.educational_institution.SchoolServiceImpl;
import com.kainv.model.service.personal_cabinet.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
//@Service
public class RegistrationServiceMediatorImpl implements IMediatorService {

    private UserServiceImpl userService;
    private SchoolServiceImpl schoolService;

//    @Autowired
//    public RegistrationServiceMediatorImpl(UserServiceImpl userService, SchoolServiceImpl schoolService) {
//        this.userService = userService;
//        this.schoolService = schoolService;
//    }

    @Override
    public void registerComponent(IComponentService iComponentService) {
        iComponentService.setMediator(this);

        switch (iComponentService.getComponentName()) {
            case "SchoolServiceImpl" -> {
                userService = (UserServiceImpl) iComponentService;
            }

            case "UserServiceImpl" -> {
                schoolService = (SchoolServiceImpl) iComponentService;
            }

        }
    }

    @Override
    public Optional<DirectorDto> addDirector(AddDirectorDto addDirectorDto) {
        DirectorDto directorDto = null;

        Optional<UserDto> newUser = userService.createEntity(addDirectorDto.getAddUserDto());
        if (newUser.isPresent()) {
            Optional<SchoolDto> newSchool = schoolService.createEntity(addDirectorDto.getAddSchoolDto());
            if (newSchool.isPresent()) {
                log.info("Director {} and school {} have been created successfully", addDirectorDto.getAddUserDto().getEmail(), addDirectorDto.getAddSchoolDto().getName());
                directorDto = DirectorDto.builder()
                        .userDto(newUser.get())
                        .schoolDto(newSchool.get())
                        .build();
            } else {
                log.info("School {} has not been saved", addDirectorDto.getAddSchoolDto().getName());
            }
        } else {
            log.info("Director {} has not been saved and school has not been saved", addDirectorDto.getAddUserDto().getEmail());
        }

        return Optional.ofNullable(directorDto);

    }
}
