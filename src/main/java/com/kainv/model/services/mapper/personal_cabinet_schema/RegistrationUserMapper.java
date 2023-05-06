package com.kainv.model.services.mapper.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.RegistrationUserDto;
import com.kainv.model.entities.personal_cabinet_schema.User.Birthday;
import com.kainv.model.entities.personal_cabinet_schema.User.BirthdayConverter;
import com.kainv.model.entities.personal_cabinet_schema.User.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring", uses = {RoleMapper.class}, imports = {BirthdayConverter.class, Birthday.class})
public interface RegistrationUserMapper {

    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "toBirthday")
    User toUser(RegistrationUserDto registrationUserDto);

    @InheritInverseConfiguration
    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "toBirthDate")
    RegistrationUserDto fromUser(User user);

    @Named("toBirthday")
    default Birthday toBirthday(String birthDate) {
        return new Birthday(LocalDate.parse(birthDate));
    }

    @Named("toBirthDate")
    default String toBirthDate(Birthday birthday) {
        return birthday.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}


