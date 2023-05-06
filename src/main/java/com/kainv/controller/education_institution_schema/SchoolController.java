package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.School;
import com.kainv.model.repos.education_institution_schema.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("schools")
public class SchoolController {
    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping("")
    public List<School> schools() {
        return schoolRepository.findAll();
    }
}