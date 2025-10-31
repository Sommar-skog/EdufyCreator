package com.example.EdufyCreator.models.dtos.mappers;

import com.example.EdufyCreator.models.dtos.CreatorResponseDTO;
import com.example.EdufyCreator.models.entities.Creator;

import java.util.ArrayList;
import java.util.List;

//ED-143-AA
public class CreatorResponseMapper {

    //ED-143-AA
    public static CreatorResponseDTO toFullDTO(Creator creator) {


        CreatorResponseDTO dto = new CreatorResponseDTO();
        dto.setId(creator.getId());
        dto.setSub(creator.getSub());
        dto.setUsername(creator.getUsername());
        dto.setActive(creator.isActive());
        dto.setVideoClips(getMediaNames(creator.getVideoClipIds()));
        dto.setVideoPlaylists(getMediaNames(creator.getVideoPlaylistIds()));
        dto.setSongs(getMediaNames(creator.getSongIds()));
        dto.setAlbums(getMediaNames(creator.getAlbumIds()));
        dto.setPodcastEpisodes(getMediaNames(creator.getPodcastEpisodeIds()));
        dto.setPodcastSeasons(getMediaNames(creator.getPodcastSeasonIds()));

        return dto;
    }

    //ED-143-AA
    private static List<String> getMediaNames (List<Long> mediaIds){
        List<String> mediaNames = new ArrayList<>();

        if (mediaIds != null) {
            for (Long mediaId : mediaIds) {
                //TODO API-anrop or update and send in from service
                String mediaName = null; //TODO Update later to name of media (song title, video title etc.)
                mediaNames.add(mediaName != null ? mediaName : "id: " + mediaId);
            }
        }
        return mediaNames;
    }

}
