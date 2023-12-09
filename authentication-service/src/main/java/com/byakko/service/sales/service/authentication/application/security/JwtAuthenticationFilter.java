package com.byakko.service.sales.service.authentication.application.security;

import com.byakko.service.sales.common.application.dto.ErrorDTO;
import com.byakko.service.sales.common.application.dto.ErrorResponse;
import com.byakko.service.sales.common.utils.JwtPayload;
import com.byakko.service.sales.common.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Value("${jwt.header-string}")
    private String HEADER_STRING;

    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());

//        if(request.getRequestURI().equals("/api/v1/auth/sa/signin") || request.getRequestURI().equals("/api/v1/auth/customers/signin")) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        try {
            String jwt = getJwtFromRequest(request);

            System.out.println("jwt = ");
            System.out.println(jwt);

            if(jwt != null && !jwt.isBlank()) {
                jwtUtils.validateToken(jwt);

                JwtPayload payload = jwtUtils.getTokenPayload(jwt);

                System.out.println(payload.getAuthorities());

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
            ex.printStackTrace();
            handleException(ex, response);
        }

        filterChain.doFilter(request, response);
    }

    private void handleException(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            ErrorResponse errorResponse = ErrorResponse.Builder.builder()
                    .error(ErrorDTO.Builder.builder()
                            .code(HttpStatus.UNAUTHORIZED.value())
                            .message(ex.getMessage())
                            .build())
                    .build();

            writer.write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException ioException) {
            log.error("Không thể gửi thông tin lỗi trở lại người dùng.", ioException);
        }
    }

}
