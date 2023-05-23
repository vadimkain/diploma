package com.kainv.model.service.educational_institution;

import com.kainv.model.dto.educational_institution.SchoolDto;

import java.util.Optional;

public interface ISchoolService<EntityDto> {
    Optional<SchoolDto> findSchoolForDirector(SchoolDto schoolDto);
}
