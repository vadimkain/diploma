package com.kainv.controller.class_journal_schema;

import com.kainv.model.entities.class_journal_schema.Attendance;
import com.kainv.model.repos.class_journal_schema.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("attendances")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("")
    public List<Attendance> attendances() {
        return attendanceRepository.findAll();
    }
}
