package com.kainv.model.repos;

import com.kainv.model.entity.personal_cabinet_domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    public Optional<User> findByEmail(String email);
}
