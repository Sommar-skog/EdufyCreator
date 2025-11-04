package com.example.EdufyCreator.services;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;

//ED-143-AA
public interface CreatorService {

    CreatorResponseDTO getCreatorById(Long id);
}
