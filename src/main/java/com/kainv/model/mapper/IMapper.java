package com.kainv.model.mapper;

public interface IMapper<Dto, Entity> {
    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);
}
