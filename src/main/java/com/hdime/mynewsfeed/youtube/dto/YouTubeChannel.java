package com.hdime.mynewsfeed.youtube.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeChannel {
    @JsonProperty("snippet.title")
    private String name;

    @JsonProperty("snippet.resourceId.channelId")
    private String id;
}
