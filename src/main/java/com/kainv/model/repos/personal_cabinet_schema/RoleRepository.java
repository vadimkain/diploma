package com.kainv.model.repos.personal_cabinet_schema;

import com.kainv.model.entities.personal_cabinet_schema.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
