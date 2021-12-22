package fr.blaze.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class TokenProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.validity}")
    private Duration validity;

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void setKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Renvoie un jeton JWT contenant les informations qui seront nécessaires
     * pour le traitement des futures requêtes.
     */
    public String createToken(String username) {
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null)
            return null;

        Claims claims = Jwts.claims().setSubject(username);

        claims.put("auth", user.getAuthorities());

        Date now = new Date();
        Date lasting = new Date(now.getTime() + validity.toMillis());

        String token = Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(now)
                .setExpiration(lasting)
                .signWith(key)
                .compact();

        return token;
    }

    /**
     * Renvoie le jeton placé dans la requête.
     *
     * Par convention, on extrait la valeur de l'en-tête `Authorization`.
     */
    public String resolve(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer "))
            return null;

        return token.substring(7);
    }

    /**
     * Permet de vérifier la validité d'un jeton JWT.
     */
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Renvoie une instance de la classe Authentication en se basant sur le
     * nom de l'utilisateur, celui-ci étant contenu dans le jeton JWT.
     */
    public Authentication authenticate(String token) {
        UserDetails details = userDetailsService.loadUserByUsername(extractUsername(token));

        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    /**
     * Permet d'extraire le nom d'utilisateur contenu dans le jeton JWT.
     */
    private String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
