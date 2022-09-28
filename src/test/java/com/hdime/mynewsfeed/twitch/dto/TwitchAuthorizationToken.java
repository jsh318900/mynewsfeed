package com.hdime.mynewsfeed.twitch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO representing the response body from twitch server when authorization token is issued.
 * IMPORTANT: TESTING PURPOSE ONLY
 */
@Getter
@Setter
@AllArgsConstructor
public class TwitchAuthorizationToken {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("scope")
    private String[] scope;
    @JsonProperty("token_type")
    private String tokenType;
}
