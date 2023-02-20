package com.company.milliyuniversity.service;

import com.company.milliyuniversity.configs.CustomAuthenticationManager;
import com.company.milliyuniversity.configs.UserDetails;
import com.company.milliyuniversity.domains.auth.AuthUser;
import com.company.milliyuniversity.dtos.JwtResponse;
import com.company.milliyuniversity.dtos.LoginRequest;
import com.company.milliyuniversity.dtos.RefreshTokenRequest;
import com.company.milliyuniversity.dtos.UserRegisterDTO;
import com.company.milliyuniversity.dtos.auth.AuthUserUpdateDto;
import com.company.milliyuniversity.exceptions.GenericInvalidTokenException;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.exceptions.GenericRuntimeException;
import com.company.milliyuniversity.mapper.AuthUserMapper;
import com.company.milliyuniversity.repository.AuthUserRepository;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.utils.jwt.TokenService;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class AuthUserService implements UserDetailsService {
    private final CustomAuthenticationManager authenticationManager;
    private final AuthUserRepository authUserRepository;
    private final TokenService accessTokenService;
    private final TokenService refreshTokenService;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthUserService(@Lazy CustomAuthenticationManager authenticationManager,
                           AuthUserRepository authUserRepository,
                           @Qualifier("accessTokenService") TokenService accessTokenService,
                           @Qualifier("refreshTokenService") TokenService refreshTokenService,
                           AuthUserMapper authUserMapper,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authUserRepository = authUserRepository;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.authUserMapper = authUserMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> exception = () ->
                new UsernameNotFoundException("Bad credentials");
        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(exception);
        return new UserDetails(authUser);
    }

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = accessTokenService.generateToken(userDetails);
        String refreshToken = refreshTokenService.generateToken(userDetails);
        return new JwtResponse(accessToken, refreshToken, "Bearer");
    }

    public JwtResponse refreshToken(@NonNull RefreshTokenRequest request) {
        String token = request.token();
        if (accessTokenService.isValid(token)) {
            throw new GenericInvalidTokenException("Refresh Token invalid", 401);
        }
        String subject = accessTokenService.getSubject(token);
        UserDetails userDetails = loadUserByUsername(subject);
        String accessToken = accessTokenService.generateToken(userDetails);
        return new JwtResponse(accessToken, request.token(), "Bearer");
    }

    @SneakyThrows
    @Transactional
    public ApiResponse<Void> register(@NonNull UserRegisterDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        AuthUser authUser = authUserMapper.fromRegisterDTO(dto);
        Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
        if (user.isPresent()) {
            throw new GenericRuntimeException("Username already exist!", 400);
        }
        if (authUserRepository.findByTelephone(authUser.getTelephone()).isPresent()) {
            throw new GenericRuntimeException("Telephone already exist!", 400);
        }
        if (authUserRepository.findByEmail(authUser.getEmail()).isPresent()) {
            throw new GenericRuntimeException("Email already exist!", 400);
        }
        authUserRepository.save(authUser);
        return new ApiResponse<>(200);
    }

    public AuthUser getSessionAuthUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return authUserRepository.findByUsername(username).orElseThrow(() -> {
            throw new GenericNotFoundException("User not found!", 404);
        });
    }

    public AuthUser update(@NonNull AuthUserUpdateDto dto) {
        Optional<AuthUser> optionalAuthUser = authUserRepository.findByUsername(dto.getUsername());
        if (optionalAuthUser.isEmpty()) {
            throw new GenericRuntimeException("User did not exist!", 400);
        }
        AuthUser authUser = optionalAuthUser.get();
        authUser.setFirstname(dto.getFirstname());
        authUser.setLastname(dto.getLastname());
        authUser.setMiddleName(dto.getMiddleName());

        if (!authUser.getTelephone().equals(dto.getTelephone())) {
            if (authUserRepository.findByTelephone(dto.getTelephone()).isPresent()) {
                throw new GenericRuntimeException("Telephone already exist!", 400);
            }
        }
        if (!authUser.getEmail().equals(dto.getEmail())) {
            if (authUserRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new GenericRuntimeException("Email already exist!", 400);
            }
        }

        authUser.setEmail(dto.getEmail());
        authUser.setTelephone(dto.getTelephone());
        return authUserRepository.save(authUser);
    }

    public List<AuthUser> getAll() {
        return authUserRepository.findAllByOrder();
    }

    public AuthUser get(Long authUserId) {
        return authUserRepository.findById(authUserId).orElse(null);
    }
}
