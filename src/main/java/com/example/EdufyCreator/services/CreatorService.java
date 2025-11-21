package com.example.EdufyCreator.services;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.MediaDTO;
import com.example.EdufyCreator.models.dtos.MediaRecordRequest;
import com.example.EdufyCreator.models.enums.MediaType;
import org.springframework.security.core.Authentication;

import java.util.List;

//ED-143-AA
public interface CreatorService {

    CreatorResponseDTO getCreatorById(Long id, Authentication auth);

    //ED-146-AA
    List<CreatorResponseDTO> getCreatorsByMediaId(MediaType mediaType, Long id);

    //ED-321-AWS
    void registerMedia(MediaRecordRequest request);

    //ED-320-AWS
    List<MediaDTO> getMediaByCreatorId(Long creatorId, MediaType mediaType);
}
