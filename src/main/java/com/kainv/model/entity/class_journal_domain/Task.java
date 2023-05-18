package com.kainv.model.entity.class_journal_domain;

import com.kainv.model.entity.education_institution_domain.TeacherWorkload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks", schema = "class_journal_schema")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_workload_id", nullable = false)
    private TeacherWorkload teacherWorkload;

    @Column(name = "begin_hw")
    private LocalDateTime beginHw;

    @Column(name = "end_hw")
    private LocalDateTime endHw;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "file_link", length = Integer.MAX_VALUE)
    private String file;
}