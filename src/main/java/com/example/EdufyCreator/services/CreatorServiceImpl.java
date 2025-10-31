package com.example.EdufyCreator.services;

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

        return null;
    }
}
