package com.kainv.model.repos.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
