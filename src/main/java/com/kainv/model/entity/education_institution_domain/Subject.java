package com.kainv.model.entity.education_institution_domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subjects", schema = "educational_institution_schema")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
}

