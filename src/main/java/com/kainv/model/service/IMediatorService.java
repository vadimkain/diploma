package com.kainv.model.service;

import com.kainv.model.dto.AddDirectorDto;
import com.kainv.model.dto.DirectorDto;

import java.util.Optional;

public interface IMediatorService {
    void registerComponent(IComponentService iComponentService);

    Optional<DirectorDto> addDirector(AddDirectorDto addDirectorDto);
}
