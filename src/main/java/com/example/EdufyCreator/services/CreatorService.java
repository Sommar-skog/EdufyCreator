package com.example.EdufyCreator.services;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.MediaRecordRequest;
import com.example.EdufyCreator.models.enums.MediaType;

import java.util.List;

//ED-143-AA
public interface CreatorService {

    CreatorResponseDTO getCreatorById(Long id);

    //ED-146-AA
    List<CreatorResponseDTO> getCreatorsByMediaId(MediaType mediaType, Long id);

    //ED-321-AWS
    void registerMedia(MediaRecordRequest request);
}
