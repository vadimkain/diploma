package com.kainv.model.repos;

import com.kainv.model.entity.personal_cabinet_domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    public User findByEmail(String email);
}
