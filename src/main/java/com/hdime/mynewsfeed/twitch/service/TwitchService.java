package com.hdime.mynewsfeed.twitch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.hdime.mynewsfeed.main.dto.AccessToken;
import com.hdime.mynewsfeed.twitch.dto.TwitchChannel;
import com.hdime.mynewsfeed.twitch.dto.TwitchStream;
import com.hdime.mynewsfeed.twitch.dto.TwitchUser;

@Service
public class TwitchService {
    private WebClient authClient;

    private WebClient apiClient;

    @Autowired
    private Environment env;

    @Autowired
    public TwitchService(@Value("${external.api.twitch.auth_endpoint}") String authUrl, @Value("${external.api.twitch.baseurl}") String baseUrl) {
        authClient = WebClient.create(authUrl);
        apiClient = WebClient.create(baseUrl);
    }

    public String getAuthorizeUrl() {
        return UriComponentsBuilder.fromPath(env.getProperty("external.api.twitch.auth_endpoint"))
                .pathSegment("authorize")
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", env.getProperty("external.api.twitch.redirect_uri"))
                .queryParam("scope", env.getProperty("external.api.twitch.scope"))
                .toUriString();
    }

    public String getAccessToken(String token) {
        return authClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment("token")
                        .queryParam("client_id", env.getProperty("${external.api.twitch.client_id}"))
                        .queryParam("client_secret", env.getProperty("${external.api.twitch.client_secret}"))
                        .queryParam("code", token)
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("redirect_uri", env.getProperty("external.api.twitch.redirect_uri"))
                        .build())
                .retrieve()
                .bodyToMono(AccessToken.class)
                .block()
                .getToken();
    }

    public String getUserID(String accessToken) {
        return apiClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment("users").build())
                .header("Authorization", "Bearer " + accessToken)
                .header("Client-Id", env.getProperty("external.api.twitch.client_id"))
                .retrieve()
                .bodyToMono(TwitchUser.class)
                .block()
                .getId();
    }

    public List<TwitchChannel> getFollowedChannels(String accessToken, String userId) {
        return apiClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment("follows")
                        .queryParam("from_id", userId)
                        .build())
                .header("Authorization", "Bearer " + accessToken)
                .header("Client-Id", env.getProperty("external.api.twitch.client_id"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TwitchChannel>>() {})
                .block();
    }

    public List<TwitchStream> getLiveStreams(String accessToken, String userId) {
        return apiClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(new String[] { "streams", "followed" })
                        .queryParam("user_id", userId)
                        .build())
                .header("Authorization", "Bearer " + accessToken)
                .header("Client-Id", env.getProperty("external.api.twitch.client_id"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TwitchStream>>() {})
                .block();
    }
}
