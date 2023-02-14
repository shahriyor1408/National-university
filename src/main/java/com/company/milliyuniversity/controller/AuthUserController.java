package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.auth.AuthUser;
import com.company.milliyuniversity.dtos.JwtResponse;
import com.company.milliyuniversity.dtos.LoginRequest;
import com.company.milliyuniversity.dtos.RefreshTokenRequest;
import com.company.milliyuniversity.dtos.UserRegisterDTO;
import com.company.milliyuniversity.dtos.auth.AuthUserUpdateDto;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.AuthUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/11/22 Monday 09:19
 * telegram-bot-app/IntelliJ IDEA
 */
@RestController
public class AuthUserController extends ApiController<AuthUserService> {
    protected AuthUserController(AuthUserService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login", produces = "application/json")
    public ApiResponse<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ApiResponse<>(service.login(loginRequest));
    }

    @PostMapping(PATH + "/auth/register")
    public ApiResponse<Void> register(@Valid @RequestBody UserRegisterDTO dto) {
        return service.register(dto);
    }

    @PutMapping(PATH + "/auth/update")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<AuthUser> update(@Valid @RequestBody AuthUserUpdateDto dto) {
        return new ApiResponse<>(service.update(dto));
    }

    @GetMapping(PATH + "/auth/me")
    @PreAuthorize("isAuthenticated()")
    public AuthUser me() {
        return service.getSessionAuthUser();
    }

    @GetMapping(PATH + "/auth/getAll")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ApiResponse<List<AuthUser>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @GetMapping(value = PATH + "/auth/refresh", produces = "application/json")
    @PreAuthorize(value = "isAuthenticated()")
    public ApiResponse<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ApiResponse<>(service.refreshToken(refreshTokenRequest));
    }
}
