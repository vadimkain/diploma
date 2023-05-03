package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.Classroom;
import com.kainv.model.repos.education_institution_schema.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("classrooms")
public class ClassroomController {
    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping("")
    public List<Classroom> schools() {
        return classroomRepository.findAll();
    }
}
