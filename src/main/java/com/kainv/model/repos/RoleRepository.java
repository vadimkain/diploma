package com.kainv.model.repos;

import com.kainv.model.entity.personal_cabinet_domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
