package com.example.EdufyCreator.repositories;

import com.example.EdufyCreator.models.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//ED-143-AA
@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {

    List<Creator> findBySongIdsContaining(Long songId);
    List<Creator> findByAlbumIdsContaining(Long albumId);
    List<Creator> findByVideoClipIdsContaining(Long videoClipId);
    List<Creator> findByVideoPlaylistIdsContaining(Long videoPlaylistId);
    List<Creator> findByPodcastEpisodeIdsContaining(Long podcastEpisodeId);
    List<Creator> findByPodcastSeasonIdsContaining(Long podcastSongId);

}
