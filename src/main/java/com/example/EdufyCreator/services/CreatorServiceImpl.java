package com.example.EdufyCreator.services;

import com.example.EdufyCreator.exceptions.ResourceNotFoundException;
import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.mappers.CreatorResponseMapper;
import com.example.EdufyCreator.models.entities.Creator;
import com.example.EdufyCreator.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //ED-146-AA
    @Override
    public List<CreatorResponseDTO> getCreatorsByMediaId(String mediaType, Long id) {
        List<Creator> creators = getCreatorsByMediaIdFromDB(mediaType, id);
        return creators.stream().map(CreatorResponseMapper::toDTOWithUsernameAndId).collect(Collectors.toList());
    }

    private List<Creator> getCreatorsByMediaIdFromDB(String mediaType, Long id) {
        List<Creator> creators = new ArrayList<>();
        mediaType = mediaType.toLowerCase();

        switch (mediaType) {
            case "song":
                creators.addAll(creatorRepository.findBySongIdsContaining(id));
                break;
            case "album":
                creators.addAll(creatorRepository.findByAlbumIdsContaining(id));
                break;
            case "videoclip":
                creators.addAll(creatorRepository.findByVideoClipIdsContaining(id));
                break;
            case "videoplaylist":
                creators.addAll(creatorRepository.findByVideoPlaylistIdsContaining(id));
                break;
            case "podcastepesode":
                creators.addAll(creatorRepository.findByPodcastEpisodeIdsContaining(id));
                break;
            case "podcastseason":
                creators.addAll(creatorRepository.findByPodcastSeasonIdsContaining(id));
                break;
            default:
                throw new ResourceNotFoundException("MediaType", "mediaType", mediaType);
        }
        return creators;
    }
}
