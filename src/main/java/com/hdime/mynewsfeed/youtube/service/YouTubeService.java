package com.hdime.mynewsfeed.youtube.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdime.mynewsfeed.youtube.dto.YouTubeChannel;
import com.hdime.mynewsfeed.youtube.dto.YouTubeVideo;

@Service
public class YouTubeService {
    public String autorize() {
        return null;
    }

    public List<YouTubeChannel> getSubscribedChannels(String accessToken) {
        return null;
    }

    public List<YouTubeVideo> getLatestVideos(String accessToken, List<String> channelIds) {
        return null;
    }
}
