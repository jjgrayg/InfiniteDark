package com.InfiniteDark.controllers;

import com.InfiniteDark.handlers.DatabaseHandler;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SessionController {

    @GetMapping("/getSession")
    public String initializeSession(@CookieValue(name = "sessionID", defaultValue = "") String sessionID, HttpServletResponse response) {
        if (!sessionID.equals("") && DatabaseHandler.checkIfSessionExists(sessionID)) {
            return sessionID;
        }
        else {
            String newSession = DatabaseHandler.createNewSession();
            Cookie cookie = new Cookie("sessionID", newSession);
            cookie.setMaxAge(60 * 24 * 60 * 60);
            response.addCookie(cookie);
            return newSession;
        }
    }


}
