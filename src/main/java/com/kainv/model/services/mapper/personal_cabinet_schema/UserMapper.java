package com.kainv.model.services.mapper.personal_cabinet_schema;

import com.kainv.model.dto.personal_cabinet_schema.User.UserDto;
import com.kainv.model.entities.personal_cabinet_schema.User.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> userList);

    User toEntity(UserDto userDto);

    List<User> toEntityList(List<UserDto> userDtoList);
}


