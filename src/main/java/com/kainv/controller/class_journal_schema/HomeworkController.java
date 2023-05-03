package com.kainv.controller.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Attendance;
import com.kainv.model.entities.class_journal_schema.Homework;
import com.kainv.model.repos.class_journal_schema.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("homeworks")
public class HomeworkController {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @GetMapping("")
    public List<Homework> homework() {
        return homeworkRepository.findAll();
    }
}
