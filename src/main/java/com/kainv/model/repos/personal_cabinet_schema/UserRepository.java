package com.kainv.model.repos.personal_cabinet_schema;

import com.kainv.model.entities.personal_cabinet_schema.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstName = :firstName AND u.surname = :surname AND u.patronymic = :patronymic")
    User findBySurnameFirstnamePatronymic(@Param("surname") String surname,
                                          @Param("firstName") String firstName,
                                          @Param("patronymic") String patronymic);
}
