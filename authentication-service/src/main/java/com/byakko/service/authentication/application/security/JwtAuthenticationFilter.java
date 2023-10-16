package com.byakko.service.authentication.application.security;

import com.byakko.common.utils.JwtPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Value("${jwt.header-string}")
    private String HEADER_STRING;

    private final JwtProvider jwtProvider;

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if(StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
                JwtPayload payload = jwtProvider.getPayload(jwt);

                // Set thông tin security context cho spring security
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        payload.getUserId(),
                        null,
                        payload.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).toList()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);
            }
        } catch (Exception ex) {
            log.error("fail on set user authentication", ex);
        }

        filterChain.doFilter(request, response);
    }
}
