package com.hdime.mynewsfeed.twitch.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdime.mynewsfeed.twitch.service.TwitchService;

@Controller
public class TwitchController {
    @Autowired
    private TwitchService twitchService;

    @RequestMapping("/twitch/login")
    public void login(HttpServletResponse response) {

        try {
            response.sendRedirect(twitchService.getAuthorizeUrl());
        } catch (IOException e) {
            System.err.println("Error occurred during twitch login redirect");
            e.printStackTrace();
        }
    }

    @RequestMapping("/twitch/login/result")
    public void loginResult(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String code,
            @RequestParam(required = false) String error) {

        if (error != null) {

            try {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Login Request Unauthorized");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (code != null) {
            String token = twitchService.getAccessToken(code);
            request.getSession().setAttribute("twitch_token", token);
        } else {

            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
