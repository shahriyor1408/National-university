package com.company.milliyuniversity;

import com.company.milliyuniversity.domains.auth.AuthRole;
import com.company.milliyuniversity.domains.auth.AuthUser;
import com.company.milliyuniversity.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class NationalUniversityApplication {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(NationalUniversityApplication.class, args);
    }

    @PostConstruct
    public void register() {
        AuthRole authRole = AuthRole.builder()
                .name("Admin")
                .code("ADMIN")
                .build();

        AuthUser authUser = AuthUser.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .firstname("Admin")
                .lastname("Admin")
                .middleName("Admin")
                .email("admin@gmail.com")
                .telephone("+998900265214")
                .roles(List.of(authRole))
                .build();
        authUserRepository.save(authUser);
    }
}
