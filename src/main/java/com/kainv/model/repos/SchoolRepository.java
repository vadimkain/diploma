package com.kainv.model.repos;

import com.kainv.model.entity.education_institution_domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    @Query("SELECT s FROM School s JOIN s.director u JOIN u.roles r WHERE u.id = :userId AND r.name = 'DIRECTOR'")
    Optional<School> findSchoolsByDirectorIdAndRole(Long userId);
}
