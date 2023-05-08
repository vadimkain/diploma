package com.kainv.model.services.education_institution_schema;

import com.kainv.model.dto.education_institution_schema.CreateSchoolDto;
import com.kainv.model.dto.education_institution_schema.SchoolDto;
import com.kainv.model.entities.education_institution_schema.School;
import com.kainv.model.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface SchoolService extends CrudService<SchoolDto, CreateSchoolDto, School> {
}
