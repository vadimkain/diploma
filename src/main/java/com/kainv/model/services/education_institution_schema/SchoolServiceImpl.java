package com.kainv.model.services.education_institution_schema;

import com.kainv.model.dto.education_institution_schema.CreateSchoolDto;
import com.kainv.model.dto.education_institution_schema.SchoolDto;
import com.kainv.model.entities.education_institution_schema.School;
import com.kainv.model.repos.education_institution_schema.SchoolRepository;
import com.kainv.model.services.mapper.education_institution_schema.CreateSchoolMapper;
import com.kainv.model.services.mapper.education_institution_schema.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    CreateSchoolMapper createSchoolMapper;
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public Optional<SchoolDto> createEntity(CreateSchoolDto createSchoolDto) {
        //        step 1 : validation

        //        step 2 : map
        School schoolEntity = createSchoolMapper.toEntity(createSchoolDto);

        //        step 3 : check at exists

        //        step 4 : insert
        Optional<School> resultInsertSchool = Optional.of(schoolRepository.save(schoolEntity));

        //        step 5 : return
        return Optional.ofNullable(schoolMapper.toDto(resultInsertSchool.get()));
    }

    @Override
    public List<SchoolDto> getAllEntities() {
        return null;
    }

    @Override
    public Optional<SchoolDto> getEntityById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<SchoolDto> updateEntity(Long id, SchoolDto EntityDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteEntity(Long id) {
        return false;
    }
}
