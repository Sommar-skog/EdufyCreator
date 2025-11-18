package com.example.EdufyCreator.models.dtos;

import com.example.EdufyCreator.models.enums.MediaType;

import java.util.List;

//ED-321-AWS
public class MediaRecordRequest {

    private Long mediaId;
    private MediaType mediaType;
    private List<Long> creatorIds;

    public MediaRecordRequest() {
    }

    public MediaRecordRequest(Long mediaId, MediaType mediaType, List<Long> creatorIds) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.creatorIds = creatorIds;
    }

    public Long getMediaId() {
        return mediaId;
    }
    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }
    public MediaType getMediaType() {
        return mediaType;
    }
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
    public List<Long> getCreatorIds() {
        return creatorIds;
    }
    public void setCreatorIds(List<Long> creatorIds) {
        this.creatorIds = creatorIds;
    }

    @Override
    public String toString() {
        return "MediaRecordRequest{" +
                "mediaId=" + mediaId +
                ", mediaType=" + mediaType +
                ", creatorIds=" + creatorIds +
                '}';
    }
}
