package com.kainv.model.repos.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
