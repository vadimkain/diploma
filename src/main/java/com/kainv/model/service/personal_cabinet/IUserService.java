package com.kainv.model.service.personal_cabinet;

import java.util.Optional;

public interface IUserService<EntityDto> {
    public Optional<EntityDto> getUserByEmail(String email);
}
