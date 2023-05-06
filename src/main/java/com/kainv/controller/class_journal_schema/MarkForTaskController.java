package com.kainv.controller.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.MarkForTask;
import com.kainv.model.repos.class_journal_schema.MarkForTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("marks-for-tasks")
public class MarkForTaskController {
    @Autowired
    private MarkForTaskRepository markForTaskRepository;

    @GetMapping("")
    public List<MarkForTask> recordBooks() {
        return markForTaskRepository.findAll();
    }
}
