package com.kainv.model.mapper.personal_cabinet;

import com.kainv.model.dto.personal_cabinet.UserDto;
import com.kainv.model.entity.personal_cabinet_domain.User;
import com.kainv.model.mapper.IMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends IMapper<UserDto, User> {
}
