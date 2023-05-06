package com.kainv.controller.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Task;
import com.kainv.model.repos.class_journal_schema.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("")
    public List<Task> homework() {
        return taskRepository.findAll();
    }
}
