package com.example.EdufyCreator.controllers;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.MediaDTO;
import com.example.EdufyCreator.models.dtos.MediaRecordRequest;
import com.example.EdufyCreator.models.enums.MediaType;
import com.example.EdufyCreator.services.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ED-257-AA
@RestController
@RequestMapping("/creator")
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

    //ED-321-AWS
    @PutMapping("/media/record")
    public ResponseEntity<Void> registerMedia(@RequestBody MediaRecordRequest request){
        creatorService.registerMedia(request);
        return ResponseEntity.ok().build();
    }

    //ED-320-AWS
    @GetMapping("/mediabycreator/{creatorId}/{mediaType}")
    public ResponseEntity<List<MediaDTO>> getMediaByCreatorId(@PathVariable Long creatorId, @PathVariable MediaType mediaType){
        List<MediaDTO> media = creatorService.getMediaByCreatorId(creatorId, mediaType);

        return ResponseEntity.ok(media);
    }
}
