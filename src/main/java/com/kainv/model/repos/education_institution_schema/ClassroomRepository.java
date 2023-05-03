package com.kainv.model.repos.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
