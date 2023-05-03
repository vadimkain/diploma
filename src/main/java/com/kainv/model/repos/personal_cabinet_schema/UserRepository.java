package com.kainv.model.repos.personal_cabinet_schema;

import com.kainv.model.entities.personal_cabinet_schema.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
