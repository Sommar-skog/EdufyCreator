package com.example.EdufyCreator.services;

import com.example.EdufyCreator.exceptions.BadRequestException;
import com.example.EdufyCreator.exceptions.ResourceNotFoundException;
import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.dtos.MediaRecordRequest;
import com.example.EdufyCreator.models.dtos.mappers.CreatorResponseMapper;
import com.example.EdufyCreator.models.entities.Creator;
import com.example.EdufyCreator.models.enums.MediaType;
import com.example.EdufyCreator.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CreatorResponseDTO getCreatorById(Long id) {
        Creator creator = creatorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Creator", "id", id));

        //TODO Get (music, video, pod lists with titles).

        return CreatorResponseMapper.toFullDTO(creator);
    }

    //ED-146-AA //ED-257-AA
    @Override
    public List<CreatorResponseDTO> getCreatorsByMediaId(MediaType mediaType, Long id) {
        List<Creator> creators = getCreatorsByMediaIdFromDB(mediaType, id);

        if (creators.isEmpty()) {
            throw new ResourceNotFoundException("Creator", mediaType + "Id", id);
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
            case POD_EPISODE:
                creators.addAll(creatorRepository.findByPodcastEpisodeIdsContaining(id));
                break;
            case POD_SEASON:
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

    //ED-321-AWS
    private void applyMediaToCreator(Creator creator, MediaType mediaType, Long mediaId){
        switch (mediaType) {
            case SONG:
                addIfMissing(creator.getSongIds(), mediaId);
                break;
            case ALBUM:
                addIfMissing(creator.getAlbumIds(), mediaId);
                break;
            case VIDEO_CLIP:
                addIfMissing(creator.getVideoClipIds(), mediaId);
                break;
            case VIDEO_PLAYLIST:
                addIfMissing(creator.getVideoPlaylistIds(), mediaId);
                break;
            case POD_EPISODE:
                addIfMissing(creator.getPodcastEpisodeIds(), mediaId);
                break;
            case POD_SEASON:
                addIfMissing(creator.getPodcastSeasonIds(), mediaId);
                break;
            default:
                throw new BadRequestException("mediaType", mediaType);
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

}
