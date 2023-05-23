package com.kainv.model.mapper.personal_cabinet;

import com.kainv.model.dto.personal_cabinet.AddUserDto;
import com.kainv.model.entity.personal_cabinet_domain.Birthday;
import com.kainv.model.entity.personal_cabinet_domain.BirthdayConverter;
import com.kainv.model.entity.personal_cabinet_domain.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
@Mapper(componentModel = "spring", uses = {RoleMapper.class}, imports = {BirthdayConverter.class, Birthday.class})
public interface AddUserMapper {
    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "toBirthday")
    User toUser(AddUserDto addUserDto);

    @InheritInverseConfiguration
    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "toBirthDate")
    AddUserDto toDto(User user);

    @Named("toBirthday")
    default Birthday toBirthday(String birthDate) {
        return new Birthday(LocalDate.parse(birthDate));
    }

    @Named("toBirthDate")
    default String toBirthDate(Birthday birthday) {
        return birthday.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
