package com.company.milliyuniversity.dtos;

public record JwtResponse(
        String accessToken,
        String refreshToken,
        String tokenType) {
}
