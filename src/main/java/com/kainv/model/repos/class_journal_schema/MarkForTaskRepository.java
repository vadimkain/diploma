package com.kainv.model.repos.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.MarkForTask;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkForTaskRepository extends JpaRepository<MarkForTask, Long> {
}
