package com.company.milliyuniversity.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {

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
