package com.kainv.model.repos.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.RecordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordBookRepository extends JpaRepository<RecordBook, Long> {
}