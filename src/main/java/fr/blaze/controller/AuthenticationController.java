package fr.blaze.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.blaze.exception.InvalidTokenException;
import fr.blaze.service.LoginService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public void login(@RequestParam(defaultValue = "") String returnUrl, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(loginService.loginUrl(returnUrl, response));
    }

    @RequestMapping(value = "/validate", method = { RequestMethod.GET, RequestMethod.POST })
    public void validate(@RequestParam String ticket, @RequestParam(defaultValue = "") String returnUrl, HttpServletResponse response) throws IOException {
        String token = loginService.validate(ticket, returnUrl, response);

        if (token != null) {
            response.addHeader("API-Token", token);
            response.sendRedirect(redirectUrl(returnUrl, token));
        } else {
            throw new InvalidTokenException("Token is missing");
        }
    }

    private String redirectUrl(String returnUrl, String token) {
        String fullUrl = "http://localhost:8081/" + decode(returnUrl);
        String separator = fullUrl.contains("?") ? "&" : "?";

        return fullUrl + separator + "token=" + token;
    }

    private String decode(String string) {
        return URLDecoder.decode(string, StandardCharsets.UTF_8);
    }
}
