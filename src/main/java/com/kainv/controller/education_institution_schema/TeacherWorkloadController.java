package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.TeacherWorkload;
import com.kainv.model.repos.education_institution_schema.TeacherWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("teachers-workloads")
public class TeacherWorkloadController {
    @Autowired
    private TeacherWorkloadRepository teacherWorkloadRepository;

    @GetMapping("")
    public List<TeacherWorkload> teachersInClassrooms() {
        return teacherWorkloadRepository.findAll();
    }
}
