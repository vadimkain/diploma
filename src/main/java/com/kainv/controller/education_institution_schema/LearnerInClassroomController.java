package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.LearnerInClassroom;
import com.kainv.model.repos.education_institution_schema.LearnerInClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("learners-in-classrooms")
public class LearnerInClassroomController {
    @Autowired
    private LearnerInClassroomRepository learnerInClassroomRepository;

    @GetMapping("")
    public List<LearnerInClassroom> schools() {
        return learnerInClassroomRepository.findAll();
    }
}
