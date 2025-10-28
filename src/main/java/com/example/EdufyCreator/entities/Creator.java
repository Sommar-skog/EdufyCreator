package com.example.EdufyCreator.entities;

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

/*    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<VideoClip> videoClips = new ArrayList<>();

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<VideoPlaylist> videoPlaylists = new ArrayList<>();

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Song> songs = new ArrayList<>();

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<PodcastEpisode> podcastEpisodes = new ArrayList<>();

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<PodcastSeason> podcastSeasons = new ArrayList<>();*/

    @Column(name = "active", nullable = false)
    private boolean active;

    public Creator() {}

    public Creator(Long id, String sub, String username, List<VideoClip> videoClips, List<VideoPlaylist> videoPlaylists, List<Song> songs, List<Album> albums, boolean active) {
        this.id = id;
        this.sub = sub;
        this.username = username;
/*        this.videoClips = videoClips;
        this.videoPlaylists = videoPlaylists;
        this.songs = songs;
        this.albums = albums;*/
        this.active = active;
    }

    public Creator(Creator creator) {
        this.id = creator.id;
        this.sub = creator.sub;
        this.username = creator.username;
/*        this.videoClips = creator.videoClips;
        this.videoPlaylists = creator.videoPlaylists;
        this.songs = creator.songs;
        this.albums = creator.albums;*/
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
/*

    public List<VideoClip> getVideoClips() {
        return videoClips;
    }

    public void setVideoClips(List<VideoClip> videoClips) {
        this.videoClips = videoClips;
    }

    public List<VideoPlaylist> getVideoPlaylists() {
        return videoPlaylists;
    }

    public void setVideoPlaylists(List<VideoPlaylist> videoPlaylists) {
        this.videoPlaylists = videoPlaylists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<PodcastEpisode> getPodcastEpisodes() {
        return podcastEpisodes;
    }

    public void setPodcastEpisodes(List<PodcastEpisode> podcastEpisodes) {
        this.podcastEpisodes = podcastEpisodes;
    }

    public List<PodcastSeason> getPodcastSeasons() {
        return podcastSeasons;
    }

    public void setPodcastSeasons(List<PodcastSeason> podcastSeasons) {
        this.podcastSeasons = podcastSeasons;
    }
*/

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
/*                ", videoClips=" + videoClips +
                ", videoPlaylists=" + videoPlaylists +
                ", songs=" + songs +
                ", albums=" + albums +
                ", podcastEpisodes=" + podcastEpisodes +
                ", podcastSeasons=" + podcastSeasons +*/
                ", active=" + active +
                '}';
    }
}
