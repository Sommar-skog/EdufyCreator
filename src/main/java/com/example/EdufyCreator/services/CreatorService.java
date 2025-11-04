package com.example.EdufyCreator.services;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;

import java.util.List;

//ED-143-AA
public interface CreatorService {

    CreatorResponseDTO getCreatorById(Long id);

    //ED-146-AA
    List<CreatorResponseDTO> getCreatorsByMediaId(String mediaType, Long id);
}
