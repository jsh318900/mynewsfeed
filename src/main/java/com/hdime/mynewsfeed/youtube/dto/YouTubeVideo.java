package com.hdime.mynewsfeed.youtube.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeVideo {
    private String id;

    @JsonProperty("snippet.channelTitle")
    private String channelName;

    @JsonProperty("snippet.thumbnails.default.url")
    private String thumbnailUrl;

    @JsonProperty("contentDetails.duration")
    private String duration;

    @JsonProperty("snippet.publishedAt")
    private LocalDateTime publishedTime;
}
