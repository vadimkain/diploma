package com.kainv.model.entities.personal_cabinet_schema.User;

import com.kainv.model.entities.personal_cabinet_schema.Role;
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
@Table(name = "users", schema = "personal_cabinet_schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String patronymic;

    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date", nullable = false)
    private Birthday birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_and_roles",
            schema = "personal_cabinet_schema",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
//    @JsonIgnoreProperties("users")
    private List<Role> roles;
}

