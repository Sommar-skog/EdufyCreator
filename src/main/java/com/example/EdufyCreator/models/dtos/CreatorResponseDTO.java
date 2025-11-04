package com.example.EdufyCreator.models.dtos;

import com.example.EdufyCreator.models.entities.Creator;

import java.util.List;

public class CreatorResponseDTO {

    private long id;
    private String sub;
    private String username;
    private List<String> videoClips;
    private List<String> videoPlaylists;
    private List<String> songs;
    private List<String> albums;
    private List<String> podcastEpisodes;
    private List<String> podcastSeasons;
    private boolean active;

    public CreatorResponseDTO() {}

    public CreatorResponseDTO(Creator c, List<String> videoClips,List<String> videoPlaylists,List<String> songs,List<String> albums,List<String> podcastEpisodes,List<String> podcastSeasons) {
        this.id = c.getId();
        this.sub = c.getSub();
        this.username = c.getUsername();
        this.videoClips = videoClips;
        this.videoPlaylists = videoPlaylists;
        this.songs = songs;
        this.albums = albums;
        this.podcastEpisodes = podcastEpisodes;
        this.podcastSeasons = podcastSeasons;
        this.active = true;
    }

    public CreatorResponseDTO(Creator c) {
        this.id = c.getId();
        this.sub = c.getSub();
        this.username = c.getUsername();
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<String> getVideoClips() {
        return videoClips;
    }

    public void setVideoClips(List<String> videoClips) {
        this.videoClips = videoClips;
    }

    public List<String> getVideoPlaylists() {
        return videoPlaylists;
    }

    public void setVideoPlaylists(List<String> videoPlaylists) {
        this.videoPlaylists = videoPlaylists;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<String> getPodcastEpisodes() {
        return podcastEpisodes;
    }

    public void setPodcastEpisodes(List<String> podcastEpisodes) {
        this.podcastEpisodes = podcastEpisodes;
    }

    public List<String> getPodcastSeasons() {
        return podcastSeasons;
    }

    public void setPodcastSeasons(List<String> podcastSeasons) {
        this.podcastSeasons = podcastSeasons;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CreatorResponseDTO{" +
                "id=" + id +
                ", sub='" + sub + '\'' +
                ", username='" + username + '\'' +
                ", videoClips=" + videoClips +
                ", videoPlaylists=" + videoPlaylists +
                ", songs=" + songs +
                ", albums=" + albums +
                ", podcastEpisodes=" + podcastEpisodes +
                ", podcastSeasons=" + podcastSeasons +
                ", active=" + active +
                '}';
    }
}
