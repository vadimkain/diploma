package com.kainv.model.entities.education_institution_schema;

import com.kainv.model.entities.personal_cabinet_schema.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "learners_in_classrooms", schema = "educational_institution_schema")
public class LearnerInClassroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "learner_id", nullable = false)
    private User learner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;
}
