package com.kainv.model.entities.class_journal_schema;

import com.kainv.model.entities.education_institution_schema.LearnerInClassroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attendances", schema = "class_journal_schema")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "learner_in_classroom_id", nullable = false)
    private LearnerInClassroom learnerInClassroom;

    @Column(name = "lesson_date")
    private LocalDateTime lessonDate;

    @Column(name = "present", nullable = false)
    private Boolean present;

    // getters and setters
}