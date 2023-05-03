package com.kainv.controller.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Homework;
import com.kainv.model.entities.class_journal_schema.Mark;
import com.kainv.model.repos.class_journal_schema.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("marks")
public class MarkController {
    @Autowired
    private MarkRepository markRepository;

    @GetMapping("")
    public List<Mark> marks() {
        return markRepository.findAll();
    }
}
