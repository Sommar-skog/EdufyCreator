package com.example.EdufyCreator.models.dtos;

//ED-320-AWS
public class MediaDTO {
    private Long mediaId;

    public MediaDTO() {
    }

    public MediaDTO(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getMediaId() {
        return mediaId;
    }
    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "MediaDTO{" +
                "mediaId=" + mediaId +
                '}';
    }
}
