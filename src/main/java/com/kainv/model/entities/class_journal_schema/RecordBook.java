package com.kainv.model.entities.class_journal_schema;

import com.kainv.model.entities.education_institution_schema.LearnerInClassroom;
import com.kainv.model.entities.education_institution_schema.TeacherInClassroom;
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
@Table(name = "record_book", schema = "class_journal_schema")
public class RecordBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_mark", nullable = false)
    private LocalDateTime dateMark;

    @ManyToOne
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "learner_in_classroom_id", nullable = false)
    private LearnerInClassroom learnerInClassroom;

    @ManyToOne
    @JoinColumn(name = "teacher_in_classroom_id", nullable = false)
    private TeacherInClassroom teacherInClassroom;

    @Column(name = "description", length = 400)
    private String description;

    // getters and setters
}
