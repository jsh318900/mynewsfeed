package com.hdime.mynewsfeed.twitch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdime.mynewsfeed.twitch.dto.TwitchAuthorizationToken;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@SpringBootTest
public class TwitchServiceTest {
    private static MockWebServer mockTwitchBackend;

    private TwitchService service;

    @Autowired
    private Environment env;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setUp() throws IOException {
        mockTwitchBackend = new MockWebServer();
    }

    @BeforeEach
    public void initialize() {
        String baseUrl = String.format("http://%s:%s", mockTwitchBackend.getHostName(), mockTwitchBackend.getPort());
        service = new TwitchService(baseUrl, baseUrl);
    }

    @Test
    public void testGetAcessToken() throws JsonProcessingException {
        TwitchAuthorizationToken mockTokenResponse = new TwitchAuthorizationToken("test_token", 0, "test_refresh", new String[] { "channel:test" }, "test_type");
        mockTwitchBackend.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockTokenResponse))
                .addHeader("Content-Type", "application/json"));
        String token = service.getAccessToken("test_code");
        assertEquals("test_token", token);
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockTwitchBackend.shutdown();
    }
}
