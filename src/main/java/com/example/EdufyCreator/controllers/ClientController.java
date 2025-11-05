package com.example.EdufyCreator.controllers;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.enums.MediaType;
import com.example.EdufyCreator.services.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-257-AA
@RestController
@RequestMapping("/creators")
public class ClientController {

    private final CreatorService creatorService;

    @Autowired
    public ClientController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    //ED-146-AA //ED-257-AA
    @GetMapping("/creators-mediaid")
    public ResponseEntity<List<CreatorResponseDTO>> getCreatorsByMediaId(@RequestParam("mediaType") MediaType mediaType, @RequestParam("id") Long id) {
        List<CreatorResponseDTO> creators = creatorService.getCreatorsByMediaId(mediaType, id);
        return ResponseEntity.ok(creators);
    }
}
