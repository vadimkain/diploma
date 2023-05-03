package com.kainv.model.repos.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
