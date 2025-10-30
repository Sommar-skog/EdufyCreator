package com.example.EdufyCreator.models.dtos;


//ED-152-AA
public class PodcastSeasonDTO {

    private Long id;
    private String title;

    public PodcastSeasonDTO() {}

    public PodcastSeasonDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PodcastSeasonDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}


