package com.worldnavigator.web.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

    private final UserDetailsService userDetailsService;

    private final JwtConfiguration jwtConfiguration;
    private final SecretKey secretKey;

    public JwtFilter(JwtConfiguration jwtConfiguration,
                     SecretKey secretKey,
                     UserDetailsService userDetailsService) {
        this.jwtConfiguration = jwtConfiguration;
        this.secretKey = secretKey;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        try {

            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String token = extractToken(request);

            if(StringUtils.hasText(token)) {

                Jws<Claims> claimsJws = Jwts
                        .parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token);

                Claims body = claimsJws.getBody();
                String username = body.getSubject();

                UserDetails user = userDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JwtException e) {
            throw new IllegalStateException("The token is not valid!");
        }
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authorizationHeader)
                && authorizationHeader.startsWith(jwtConfiguration.getPrefix() + " "))
            return authorizationHeader.split(" ")[1];

        return null;
    }
}
