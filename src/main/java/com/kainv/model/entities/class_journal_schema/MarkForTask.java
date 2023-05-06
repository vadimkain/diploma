package com.kainv.model.entities.class_journal_schema;

import com.kainv.model.entities.education_institution_schema.LearnerInClassroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "marks_for_tasks", schema = "class_journal_schema")
public class MarkForTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_mark", nullable = false)
    private LocalDateTime dateMark;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "percent_mark")
    private Float mark;

    @ManyToOne
    @JoinColumn(name = "learner_in_classroom_id", nullable = false)
    private LearnerInClassroom learnerInClassroom;

    @Column(name = "description", length = 400)
    private String description;
}
