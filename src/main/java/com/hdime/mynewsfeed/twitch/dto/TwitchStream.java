package com.hdime.mynewsfeed.twitch.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchStream {
    @JsonProperty("user_login")
    private String userName;

    private String title;

    @JsonProperty("started_at")
    private LocalDateTime startTime;

    @JsonProperty("game_name")
    private String gameName;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
}
