package com.example.EdufyCreator.controllers;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.services.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        @GetMapping("/creator/{id}")
        public ResponseEntity<CreatorResponseDTO> getCreatorById(@PathVariable Long id) {
               return ResponseEntity.ok(creatorService.getCreatorById(id));
        }
}
