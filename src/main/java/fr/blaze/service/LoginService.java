package fr.blaze.service;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    String serviceUrl(String returnUrl, HttpServletResponse response);

    String loginUrl(String returnUrl, HttpServletResponse response);

    String validate(String ticket, String returnUrl, HttpServletResponse response);
}
