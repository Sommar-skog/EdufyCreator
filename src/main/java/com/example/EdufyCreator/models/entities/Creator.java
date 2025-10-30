package com.example.EdufyCreator.models.entities;

import com.example.EdufyCreator.models.dtos.AlbumDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//ED-147-AA
@Entity
@Table(name = "creator")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub", unique = true, nullable = false)
    private String sub;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    //ED-150-AA
    @ElementCollection
    @CollectionTable(
            name = "creator_video_clips",
            joinColumns = @JoinColumn(name = "creator_id")
    )
    @Column(name = "video_clip_id")
    private List<Long> videoClipIds = new ArrayList<>();

    //ED-153-AA
    @ElementCollection
    @CollectionTable(
            name = "creator_video_playlist",
            joinColumns = @JoinColumn(name = "creator_id")
    )
    @Column(name = "video_playlist_id")
    private List<Long> videoPlaylistIds = new ArrayList<>();

    //ED-148-AA
    @ElementCollection
    @CollectionTable(
            name = "creator_songs",
            joinColumns = @JoinColumn(name = "creator_id")
    )
    @Column(name = "song_id")
    private List<Long> songIds = new ArrayList<>();

    //ED-151-AA
    @ElementCollection
    @CollectionTable(
            name = "creator_albums",
            joinColumns = @JoinColumn(name = "creator_id")
    )
    @Column(name = "album_id")
    private List<Long> albumIds = new ArrayList<>();

    //ED-149-AA
    @ElementCollection
    @CollectionTable(
            name = "creator_podcast_episodes",
            joinColumns = @JoinColumn(name = "creator_id")
    )
    @Column(name = "podcast_episode_id")
    private List<Long> podcastEpisodeIds = new ArrayList<>();

    /*@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<PodcastSeason> podcastSeasons = new ArrayList<>();*/

    @Column(name = "active", nullable = false)
    private boolean active;

    public Creator() {}

    public Creator(Long id, String sub, String username, List<Long> videoClipIds, List<Long> videoPlaylistIds, List<Long> songIds, List<Long> albumIds,List<Long> podcastEpisodeIds, boolean active) {
        this.id = id;
        this.sub = sub;
        this.username = username;
        this.videoClipIds = videoClipIds;
        this.videoPlaylistIds = videoPlaylistIds;
        this.songIds = songIds;
        this.albumIds = albumIds;
        this.podcastEpisodeIds = podcastEpisodeIds;
        this.active = active;
    }

    public Creator(Creator creator) {
        this.id = creator.id;
        this.sub = creator.sub;
        this.username = creator.username;
        this.videoClipIds = creator.videoClipIds;
        this.videoPlaylistIds = creator.videoPlaylistIds;
        this.songIds = creator.songIds;
        this.albumIds = creator.albumIds;
        this.podcastEpisodeIds = creator.podcastEpisodeIds;
        this.active = creator.active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getVideoClipIds() {
        return videoClipIds;
    }

    public void setVideoClipIds(List<Long> videoClipIds) {
        this.videoClipIds = videoClipIds;
    }

    public List<Long> getVideoPlaylistIds() {
        return videoPlaylistIds;
    }

    public void setVideoPlaylistIds(List<Long> videoPlaylistIds) {
        this.videoPlaylistIds = videoPlaylistIds;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    public List<Long> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        this.albumIds = albumIds;
    }

    public List<Long> getPodcastEpisodeIds() {
        return podcastEpisodeIds;
    }

    public void setPodcastEpisodeIds(List<Long> podcastEpisodeIds) {
        this.podcastEpisodeIds = podcastEpisodeIds;
    }

    /*    public List<PodcastSeason> getPodcastSeasons() {
        return podcastSeasons;
    }

    public void setPodcastSeasons(List<PodcastSeason> podcastSeasons) {
        this.podcastSeasons = podcastSeasons;
    }*/

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", sub='" + sub + '\'' +
                ", username='" + username + '\'' +
                ", videoClipIds=" + videoClipIds +
                ", videoPlaylistIds=" + videoPlaylistIds +
                ", songIds=" + songIds +
                ", albumIds=" + albumIds +
                ", podcastEpisodeIds=" + podcastEpisodeIds +
                //", podcastSeasons=" + podcastSeasons +
                ", active=" + active +
                '}';
    }
}
