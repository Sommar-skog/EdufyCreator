package com.example.EdufyCreator.controllers;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.services.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//ED-146-AA
@RestController
@RequestMapping("/creator")
public class UserController {

    //ED-146-AA
    private final CreatorService creatorService;

    //ED-146-AA
    @Autowired
    public UserController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    //ED-146-AA
    @GetMapping("/creatorbymediaid")
    public ResponseEntity<CreatorResponseDTO> getCreatorByMediaId(@RequestParam("mediaType") String mediaType, @RequestParam("id") Long id) {
        return ResponseEntity.ok(getCreatorByMediaId(mediaType,id));
    }


}
