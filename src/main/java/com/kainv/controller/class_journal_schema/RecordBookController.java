package com.kainv.controller.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Mark;
import com.kainv.model.entities.class_journal_schema.RecordBook;
import com.kainv.model.repos.class_journal_schema.RecordBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("record-books")
public class RecordBookController {
    @Autowired
    private RecordBookRepository recordBookRepository;

    @GetMapping("")
    public List<RecordBook> recordBooks() {
        return recordBookRepository.findAll();
    }
}
