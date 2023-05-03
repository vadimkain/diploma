package com.kainv.controller.education_institution_schema;

import com.kainv.model.entities.education_institution_schema.Schedule;
import com.kainv.model.repos.education_institution_schema.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("schedules")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("")
    public List<Schedule> schools() {
        return scheduleRepository.findAll();
    }
}
