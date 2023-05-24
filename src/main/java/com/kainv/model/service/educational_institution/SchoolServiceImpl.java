package com.kainv.model.service.educational_institution;

import com.kainv.exceptions.SchoolAlreadyExistsForUserException;
import com.kainv.exceptions.SchoolForUsersNotFoundException;
import com.kainv.model.dto.educational_institution.AddSchoolDto;
import com.kainv.model.dto.educational_institution.SchoolDto;
import com.kainv.model.entity.education_institution_domain.School;
import com.kainv.model.mapper.educational_institution.AddSchoolMapper;
import com.kainv.model.mapper.educational_institution.SchoolMapper;
import com.kainv.model.repos.SchoolRepository;
import com.kainv.model.service.ICrudService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SchoolServiceImpl implements ICrudService<SchoolDto, AddSchoolDto>, ISchoolService<SchoolDto> {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final AddSchoolMapper addSchoolMapper;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolMapper schoolMapper, AddSchoolMapper addSchoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
        this.addSchoolMapper = addSchoolMapper;
    }

    @Transactional
    @Override
    public Optional<SchoolDto> createEntity(AddSchoolDto addSchoolDto) {
        try {
            Optional<School> existingSchool = schoolRepository.findSchoolsByDirectorIdAndRole(addSchoolDto.getDirector().getId());
            if (existingSchool.isPresent()) {
                log.info("School {} has been found for director {}", addSchoolDto.getName(), addSchoolDto.getDirector().getEmail());
                throw new SchoolAlreadyExistsForUserException("School for user already exists");
            }

            School savedSchool = schoolRepository.save(addSchoolMapper.toEntity(addSchoolDto));
            log.info("School {} has been saved", savedSchool);

            return Optional.of(schoolMapper.toDto(savedSchool));
        } catch (SchoolAlreadyExistsForUserException e) {
            log.error("Error creating school: {}", e.getMessage());
            return Optional.empty();
        }
    }


    @Transactional
    @Override
    public Optional<SchoolDto> updateEntity(SchoolDto schoolDto) {
        try {
            Optional<School> schoolById = schoolRepository.findById(schoolDto.getId());

            if (schoolById.isPresent()) {
                School school = schoolById.get();
                school.setName(schoolDto.getName());
                school.setDescription(schoolDto.getDescription());

                School updatedSchool = schoolRepository.save(school);

                log.info("School with id {} and name {} has been updated", updatedSchool.getId(), updatedSchool.getName());

                return Optional.of(schoolMapper.toDto(updatedSchool));
            } else {
                log.info("School with id {} does not exist", schoolDto.getId());
                throw new SchoolForUsersNotFoundException("School for user not found");
            }
        } catch (SchoolForUsersNotFoundException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public boolean deleteEntity(Long id) {
        try {
            Optional<School> schoolById = schoolRepository.findById(id);

            if (schoolById.isPresent()) {
                School school = schoolById.get();
                schoolRepository.deleteById(school.getId());

                log.info("School with id {} and name {} has been deleted", school.getId(), school.getName());

                return true;
            } else {
                log.info("School with id {} has not been deleted because it was not found", id);
                throw new SchoolForUsersNotFoundException("School for user not found");
            }
        } catch (SchoolForUsersNotFoundException e) {
            log.info("School with id {} does not exist", id);
            return false;
        }
    }


    @Override
    public Optional<SchoolDto> findSchoolForDirector(SchoolDto schoolDto) {
        Optional<School> schoolOptional = schoolRepository.findSchoolsByDirectorIdAndRole(schoolDto.getDirector().getId());

        if (schoolOptional.isPresent()) {
            School school = schoolOptional.get();
            log.info("School {} has been found for director {}", schoolDto.getName(), schoolDto.getDirector().getEmail());
            return Optional.of(schoolMapper.toDto(school));
        } else {
            log.info("School {} has not been found for director {}", schoolDto.getName(), schoolDto.getDirector().getEmail());
            return Optional.empty();
        }
    }

}
