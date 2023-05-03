package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.TeacherInClassroom;
import com.kainv.model.repos.education_institution_schema.TeacherInClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("teachers-in-classrooms")
public class TeacherInClassroomController {
    @Autowired
    private TeacherInClassroomRepository teacherInClassroomRepository;

    @GetMapping("")
    public List<TeacherInClassroom> teachersInClassrooms() {
        return teacherInClassroomRepository.findAll();
    }
}
