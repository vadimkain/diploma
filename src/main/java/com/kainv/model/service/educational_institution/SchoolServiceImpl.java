package com.kainv.model.service.educational_institution;

import com.kainv.model.dto.educational_institution.AddSchoolDto;
import com.kainv.model.dto.educational_institution.SchoolDto;
import com.kainv.model.entity.education_institution_domain.School;
import com.kainv.model.mapper.educational_institution.AddSchoolMapper;
import com.kainv.model.mapper.educational_institution.SchoolMapper;
import com.kainv.model.repos.SchoolRepository;
import com.kainv.model.service.ICrudService;
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

    @Override
    public Optional<SchoolDto> createEntity(AddSchoolDto addSchoolDto) {

        if (schoolRepository.findSchoolsByDirectorIdAndRole(addSchoolDto.getDirector().getId()).isPresent()) {
            log.info("school {} has found for director {}", addSchoolDto.getName(), addSchoolDto.getDirector().getEmail());
            return Optional.empty();
        }

        Optional<School> resultInsertSchool = Optional.of(schoolRepository.save(addSchoolMapper.toEntity(addSchoolDto)));

        resultInsertSchool.ifPresentOrElse(school -> log.info("school: {} has saved", school), () -> log.info("school has not saved"));

        return resultInsertSchool.map(schoolMapper::toDto);
    }

    @Override
    public Optional<SchoolDto> updateEntity(SchoolDto schoolDto) {
        Optional<School> schoolById = schoolRepository.findById(schoolDto.getId());

        Optional<SchoolDto> updatedUserDtoOptional = schoolById.map(school -> {

            school.setName(schoolDto.getName());
            school.setDescription(schoolDto.getDescription());

            schoolRepository.save(school);

            log.info("school with id {} and name {} has updated", school.getId(), school.getName());

            return schoolMapper.toDto(school);
        });

        log.info("school with id {} and name {} does not exist", schoolDto.getId(), schoolDto.getName());

        return Optional.empty();
    }

    @Override
    public boolean deleteEntity(Long id) {
        Optional<School> schoolById = schoolRepository.findById(id);

        schoolById.map(school -> {
            schoolRepository.deleteById(school.getId());

            log.info("school with id {} and name {} has deleted", school.getId(), school.getName());

            return true;
        });

        log.info("school with id {} has not deleted because he has not found", id);
        return false;
    }

    @Override
    public Optional<SchoolDto> findSchoolForDirector(SchoolDto schoolDto) {
        schoolRepository.findSchoolsByDirectorIdAndRole(schoolDto.getDirector().getId())
                .map(
                        school -> {
                            log.info("school {} has found for director {}", schoolDto.getName(), schoolDto.getDirector().getEmail());

                            return Optional.of(schoolMapper.toDto(school));
                        });

        log.info("school {} has not found for director {}", schoolDto.getName(), schoolDto.getDirector().getEmail());

        return Optional.empty();
    }
}
