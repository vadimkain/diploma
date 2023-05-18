package com.kainv.model.entity.education_institution_domain;

import com.kainv.model.entity.personal_cabinet_domain.User;
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
@Table(name = "schools", schema = "educational_institution_schema")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User director;

    // getters and setters
}

