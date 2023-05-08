package com.kainv.model.services.education_institution_schema;

import com.kainv.model.dto.education_institution_schema.CreateSchoolDto;
import com.kainv.model.dto.education_institution_schema.SchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaseSchoolServiceDecorator implements SchoolService {

    @Autowired
    SchoolService schoolService;

    @Override
    public Optional<SchoolDto> createEntity(CreateSchoolDto createSchoolDto) {
        return schoolService.createEntity(createSchoolDto);
    }

    @Override
    public List<SchoolDto> getAllEntities() {
        return schoolService.getAllEntities();
    }

    @Override
    public Optional<SchoolDto> getEntityById(Long id) {
        return schoolService.getEntityById(id);
    }

    @Override
    public Optional<SchoolDto> updateEntity(Long id, SchoolDto entityDto) {
        return schoolService.updateEntity(id, entityDto);
    }

    @Override
    public boolean deleteEntity(Long id) {
        return schoolService.deleteEntity(id);
    }
}
