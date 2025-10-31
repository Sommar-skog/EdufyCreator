package com.example.EdufyCreator.services;

import com.example.EdufyCreator.exceptions.ResourceNotFoundException;
import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.mappers.CreatorResponseMapper;
import com.example.EdufyCreator.models.entities.Creator;
import com.example.EdufyCreator.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//ED-143-AA
@Service
public class CreatorServiceImpl implements CreatorService {

    //ED-143-AA
    private final CreatorRepository creatorRepository;

    //ED-143-AA
    @Autowired
    public CreatorServiceImpl(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    //ED-143-AA
    @Override
    public CreatorResponseDTO getCreatorById(Long id) {
        Creator creator = creatorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Creator", "id", id));

        //TODO Get (music, video, pod lists with titles).

        return CreatorResponseMapper.toFullDTO(creator);
    }
}
