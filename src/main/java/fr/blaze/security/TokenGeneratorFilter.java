package fr.blaze.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class TokenGeneratorFilter extends OncePerRequestFilter {

    private TokenProvider tokenProvider;

    public TokenGeneratorFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getParameter("token");

        String currentToken = tokenProvider.resolve(request);
        if (currentToken == null || !currentToken.equals(token)) {
            response.addHeader("Token", token);
        }

        filterChain.doFilter(request, response);
    }
}