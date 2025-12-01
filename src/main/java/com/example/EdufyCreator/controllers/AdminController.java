package com.example.EdufyCreator.controllers;

import com.example.EdufyCreator.models.dtos.CreateCreatorDTO;
import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.services.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//ED-143-AA
@RestController
@PreAuthorize("hasAuthority('ROLE_creator_admin')")
@RequestMapping("/creator")
public class AdminController {

    private final CreatorService creatorService;

    @Autowired
    public AdminController(CreatorService creatorService) {
            this.creatorService = creatorService;
    }

    //ED-143-AA
    @GetMapping("/creator/{id}")
    public ResponseEntity<CreatorResponseDTO> getCreatorById(@PathVariable Long id, Authentication auth) {
           return ResponseEntity.ok(creatorService.getCreatorById(id, auth));
        }

    //ED-332-AWS
    @PostMapping("create-creator")
    public ResponseEntity<CreatorResponseDTO> createCreator(@RequestBody CreateCreatorDTO dto) {
            CreatorResponseDTO created = creatorService.createCreator(dto);
            return  ResponseEntity.status(201).body(created);
    }
}
