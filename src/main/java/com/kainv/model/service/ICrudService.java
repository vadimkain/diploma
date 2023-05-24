package com.kainv.model.service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICrudService<EntityDto, AddEntityDto> {
    @Transactional
    Optional<EntityDto> createEntity(AddEntityDto addEntityDto);

    default List<EntityDto> getAllEntities() {
        return null;
    }

    default Optional<EntityDto> getEntityById(Long id) {
        return Optional.empty();
    }

    @Transactional
    Optional<EntityDto> updateEntity(EntityDto entityDto);

    @Transactional
    boolean deleteEntity(Long id);
}
