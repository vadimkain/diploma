package com.kainv.model.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<EntityDto, InsertEntityDto, Entity> {
    Optional<EntityDto> createEntity(InsertEntityDto insertEntityDto);

    List<EntityDto> getAllEntities();

    Optional<EntityDto> getEntityById(Long id);

    Optional<EntityDto> updateEntity(Long id, EntityDto EntityDto);

    boolean deleteEntity(Long id);
}
