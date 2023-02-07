package com.company.milliyuniversity.configs;

import com.company.milliyuniversity.response.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 06/02/23 Monday 11:56
 * milliy-university/IntelliJ IDEA
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("Unauthorized error: {}", authException.getMessage());
        ServletOutputStream outputStream = response.getOutputStream();
        ApiErrorResponse failureResponse = ApiErrorResponse.builder()
                .friendlyMessage(authException.getMessage())
                .developerMessage(authException.getMessage())
                .requestPath(request.getRequestURI())
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        objectMapper.writeValue(outputStream, failureResponse);
    }
}
