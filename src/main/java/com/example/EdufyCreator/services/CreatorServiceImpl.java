package com.example.EdufyCreator.services;

import com.example.EdufyCreator.exceptions.BadRequestException;
import com.example.EdufyCreator.exceptions.ResourceNotFoundException;
import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.MediaDTO;
import com.example.EdufyCreator.models.dtos.MediaRecordRequest;
import com.example.EdufyCreator.models.dtos.mappers.CreatorResponseMapper;
import com.example.EdufyCreator.models.entities.Creator;
import com.example.EdufyCreator.models.enums.MediaType;
import com.example.EdufyCreator.repositories.CreatorRepository;
import com.example.EdufyCreator.services.util.ValidateMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    public CreatorResponseDTO getCreatorById(Long id, Authentication auth) {
        Creator creator = creatorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Creator", "id", id));

        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_microservice_access"))) {
            return CreatorResponseMapper.toDTOClientCallJustId(creator);
        }

        //TODO Get (music, video, pod lists with titles) for FullDTO.
        return CreatorResponseMapper.toFullDTO(creator);
    }

    //ED-146-AA //ED-257-AA
    @Override
    public List<CreatorResponseDTO> getCreatorsByMediaId(String mediaType, Long id) {
        MediaType mt = ValidateMediaType.getTypeFromClientCall(mediaType);
        List<Creator> creators = getCreatorsByMediaIdFromDB(mt, id);

        if (creators.isEmpty()) {
            throw new ResourceNotFoundException("Creator", mt + "Id", id);
        }

        return creators.stream().map(CreatorResponseMapper::toDTOWithUsernameAndId).collect(Collectors.toList());
    }

    //ED-321-AWS
    @Override
    public void registerMedia(MediaRecordRequest request) {
        validateMediaRecordRequest(request);

        Long mediaId = request.getMediaId();
        MediaType mediaType = request.getMediaType();
        List<Long> creatorIds = request.getCreatorIds();

        List<Creator> creators = getCreatorOrThrow(creatorIds);

        for (Creator creator : creators) {
            applyMediaToCreator(creator, mediaType, mediaId);
        }

        creatorRepository.saveAll(creators);
    }

    @Override
    public List<MediaDTO> getMediaByCreatorId(Long creatorId, MediaType mediaType) {
        validateGetMediaRequest(creatorId, mediaType);

        Creator creator = getCreatorOrThrow(creatorId);

        List<Long> mediaIds = extractMediaIds(creator, mediaType);

        return mediaIds.stream()
                .map(MediaDTO::new)
                .collect(Collectors.toList());
    }

    //ED-146-AA //ED-257-AA
    private List<Creator> getCreatorsByMediaIdFromDB(MediaType mediaType, Long id) {
        List<Creator> creators = new ArrayList<>();

        switch (mediaType) {
            case SONG:
                creators.addAll(creatorRepository.findBySongIdsContaining(id));
                break;
            case ALBUM:
                creators.addAll(creatorRepository.findByAlbumIdsContaining(id));
                break;
            case VIDEO_CLIP:
                creators.addAll(creatorRepository.findByVideoClipIdsContaining(id));
                break;
            case VIDEO_PLAYLIST:
                creators.addAll(creatorRepository.findByVideoPlaylistIdsContaining(id));
                break;
            case PODCAST_EPISODE:
                creators.addAll(creatorRepository.findByPodcastEpisodeIdsContaining(id));
                break;
            case PODCAST_SEASON:
                creators.addAll(creatorRepository.findByPodcastSeasonIdsContaining(id));
                break;
            default:
                throw new ResourceNotFoundException("MediaType", "mediaType", mediaType);
        }
        return creators;
    }

    //ED-321-AWS
    private void validateMediaRecordRequest(MediaRecordRequest request) {
        if(request == null){
            throw new BadRequestException("request", null);
        }

        if(request.getMediaId() == null){
            throw new BadRequestException("mediaId", request.getMediaId());
        }

        if(request.getMediaType() == null){
            throw new BadRequestException("mediaType", request.getMediaType());
        }

        List<Long> creatorIds = request.getCreatorIds();
        if(creatorIds == null || creatorIds.isEmpty()) {
            throw new BadRequestException("creatorIds", creatorIds);
        }
    }

    //ED-321-AWS
    private List<Creator> getCreatorOrThrow(List<Long> creatorIds){
        List<Creator> creators = creatorRepository.findAllById(creatorIds);

        if(creators.isEmpty()) {
            throw new ResourceNotFoundException("Creator", "ids", creatorIds);
        }

        Set<Long> foundIds = creators.stream()
                .map(Creator::getId)
                .collect(Collectors.toSet());
        List<Long> missing = creatorIds.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        if(!missing.isEmpty()) {
            throw new ResourceNotFoundException("Creator", "ids", missing);
        }

        return creators;
    }

    //ED-321-AWS & ED-320-AWS
    private void applyMediaToCreator(Creator creator, MediaType mediaType, Long mediaId){
        switch (mediaType) {
            case SONG -> addIfMissing(creator.getSongIds(), mediaId);
            case ALBUM -> addIfMissing(creator.getAlbumIds(), mediaId);
            case VIDEO_CLIP  -> addIfMissing(creator.getVideoClipIds(), mediaId);
            case VIDEO_PLAYLIST -> addIfMissing(creator.getVideoPlaylistIds(), mediaId);
            case PODCAST_EPISODE  -> addIfMissing(creator.getPodcastEpisodeIds(), mediaId);
            case PODCAST_SEASON  -> addIfMissing(creator.getPodcastSeasonIds(), mediaId);
            default -> throw new BadRequestException("mediaType", mediaType);
        }
    }

    //ED-321-AWS
    private void addIfMissing(List<Long> ids, Long id){
        if(id == null){
            return;
        }
        if(!ids.contains(id)){
            ids.add(id);
        }
    }

    //ED-320-AWS
    private void validateGetMediaRequest(Long creatorId, MediaType mediaType){
        if(creatorId == null){
            throw new BadRequestException("creatorId", null);
        }
        if(mediaType == null){
            throw new BadRequestException("mediaType", null);
        }
    }

    //ED-320-AWS
    private Creator getCreatorOrThrow(Long creatorId){
        return creatorRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Creator", "id", creatorId));
    }

    //ED-320-AWS
    private List<Long> extractMediaIds(Creator creator, MediaType mediaType){
        return switch (mediaType) {
            case SONG -> creator.getSongIds();
            case ALBUM -> creator.getAlbumIds();
            case VIDEO_CLIP -> creator.getVideoClipIds();
            case VIDEO_PLAYLIST -> creator.getVideoPlaylistIds();
            case PODCAST_EPISODE -> creator.getPodcastEpisodeIds();
            case PODCAST_SEASON -> creator.getPodcastSeasonIds();
        };
    }
}
