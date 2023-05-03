package com.kainv.model.repos.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
