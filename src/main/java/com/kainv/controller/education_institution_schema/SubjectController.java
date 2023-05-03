package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.Subject;
import com.kainv.model.repos.education_institution_schema.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("")
    public List<Subject> subjects() {
        return subjectRepository.findAll();
    }
}
