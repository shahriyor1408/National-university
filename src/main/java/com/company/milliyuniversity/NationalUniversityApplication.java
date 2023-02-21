package com.company.milliyuniversity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class NationalUniversityApplication {

//    private final AuthUserRepository authUserRepository;
//    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(NationalUniversityApplication.class, args);
    }

//    @PostConstruct
//    public void register() {
//        AuthRole authRole = AuthRole.builder()
//                .name("Admin")
//                .code("ADMIN")
//                .build();
//
//        AuthUser authUser = AuthUser.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .firstname("Admin")
//                .lastname("Admin")
//                .middleName("Admin")
//                .email("admin@gmail.com")
//                .telephone("+998901234567")
//                .roles(List.of(authRole))
//                .build();
//        authUserRepository.save(authUser);
//    }
}
