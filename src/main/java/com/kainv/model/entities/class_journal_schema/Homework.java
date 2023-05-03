package com.kainv.model.entities.class_journal_schema;

import com.kainv.model.entities.education_institution_schema.TeacherInClassroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "homeworks", schema = "class_journal_schema")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_in_classroom_id", nullable = false)
    private TeacherInClassroom teacherInClassroom;

    @Column(name = "begin_hw")
    private LocalDateTime beginHw;

    @Column(name = "end_hw")
    private LocalDateTime endHw;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "file", length = Integer.MAX_VALUE)
    private String file;

    // getters and setters
}
