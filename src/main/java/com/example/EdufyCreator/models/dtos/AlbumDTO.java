package com.example.EdufyCreator.models.dtos;

//ED-151-AA
public class AlbumDTO {

    private Long id;
    private String title;

    public AlbumDTO() {}

    public AlbumDTO(Long id, String title) {
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
        return "AlbumDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
