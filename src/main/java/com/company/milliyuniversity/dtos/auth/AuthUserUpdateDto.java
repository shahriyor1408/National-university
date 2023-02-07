package com.company.milliyuniversity.dtos.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 14:18
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserUpdateDto {
    @NotBlank(message = "Firstname can not be blank")
    @NotNull(message = "Firstname can not be null!")
    private String firstname;

    @NotBlank(message = "Lastname can not be blank")
    @NotNull(message = "Lastname can not be null!")
    private String lastname;

    @NotBlank(message = "MiddleName can not be blank")
    @NotNull(message = "MiddleName can not be null!")
    private String middleName;

    @NotBlank(message = "Telephone can not be blank!")
    @NotNull(message = "Telephone can not be null!")
    private String telephone;

    @NotBlank(message = "Email can not be blank!")
    @NotNull(message = "Email can not be null!")
    private String email;

    @NotBlank(message = "Username can not be blank")
    @NotNull(message = "Username can not be null!")
    private String username;

    @NotBlank(message = "Password can not be blank")
    @NotNull(message = "Password can not be null!")
    private String password;
}
