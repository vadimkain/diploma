package com.kainv.model.entities.personal_cabinet_schema;


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
@Table(name = "privileges", schema = "personal_cabinet_schema")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    public Privilege(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //    @ManyToMany(mappedBy = "privileges")
//    @JsonIgnore
//    private Set<Role> roles = new HashSet<>();

    // getters and setters
}
