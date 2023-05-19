package com.kainv.model.service;

import com.kainv.model.dto.AddDirectorDto;
import com.kainv.model.dto.DirectorDto;

import java.util.Optional;

public interface IMediatorService {

    Optional<DirectorDto> addDirector(AddDirectorDto addDirectorDto);
}
