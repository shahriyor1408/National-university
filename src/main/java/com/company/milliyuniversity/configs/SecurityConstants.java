package com.company.milliyuniversity.configs;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/11/22 Monday 09:19
 * telegram-bot-app/IntelliJ IDEA
 */

public class SecurityConstants {
    public static final String[] WHITE_LIST = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/swagger-ui/**",
            "/api-docs",
            "/api/v1/article/download/**",
            "/api/v1/session/getAll",
            "/api/v1/speaker/getAll",
            "/api/v1/speaker/getAllInvited",
            "/api/v1/appDocument/getAll",
            "/api/v1/media/getAll",
            "/media",
            "/home/**",
            "/media/**",
            "/get-file"
    };
}
