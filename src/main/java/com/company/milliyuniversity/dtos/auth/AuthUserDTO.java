package com.company.milliyuniversity.dtos.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDTO {
    private Long id;
    private String username;
    private String email;
    private String status;
}
