package com.kainv.model.repos.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
