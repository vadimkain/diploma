package com.kainv.model.entities.education_institution_schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kainv.model.entities.personal_cabinet_schema.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "classrooms", schema = "educational_institution_schema")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
//    @JsonIgnoreProperties("classrooms")
    private School school;

    @ManyToOne
    @JoinColumn(name = "classroom_teacher_id", nullable = false)
    private User teacherLeader;

    @ManyToOne
    @JoinColumn(name = "classroom_learner_leader_id")
    private User learnerLeader;

//    @OneToMany(mappedBy = "classroom")
//    @JsonIgnoreProperties("classroom")
//    private List<TeacherInClassroom> teachersInClassrooms;
//
//    @OneToMany(mappedBy = "classroom")
//    @JsonIgnoreProperties("classroom")
//    private List<LearnerInClassroom> learnersInClassrooms;
}