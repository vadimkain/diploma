package com.kainv.model.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<EntityDto, AddEntityDto> {
    Optional<EntityDto> createEntity(AddEntityDto addEntityDto);

    default List<EntityDto> getAllEntities() {
        return null;
    }

    default Optional<EntityDto> getEntityById(Long id) {
        return Optional.empty();
    }

    Optional<EntityDto> updateEntity(EntityDto entityDto);

    boolean deleteEntity(Long id);
}
