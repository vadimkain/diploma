package com.kainv.model.repos.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
