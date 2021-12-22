package fr.blaze.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    public TokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = tokenProvider.resolve(request);

        if (token == null || !tokenProvider.validate(token)) {
            SecurityContextHolder.clearContext();

            response.setStatus(401);

        } else {
            Authentication authentication = tokenProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }
    }
}
