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

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
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
    private List<PodcastSeason> podcastSeasons = new ArrayList<>();

    @Column(name = "active", nullable = false)
    private boolean active;
}
