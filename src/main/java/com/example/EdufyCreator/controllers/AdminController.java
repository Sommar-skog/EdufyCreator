package com.example.EdufyCreator.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//ED-143-AA
@RestController
@RequestMapping("/creator")
public class AdminController {

        GetMapping("/creator/{id}")
        public ResponseEntity<CreatorResponseDTO> getCreatorById(@PathVariable int id) {

        }
}
