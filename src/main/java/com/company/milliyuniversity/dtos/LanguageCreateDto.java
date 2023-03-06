package com.company.milliyuniversity.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 01/03/23 Wednesday 18:29
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageCreateDto {

    @NotBlank(message = "Name can not be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;

    @NotBlank(message = "Code can not be blank!")
    @NotNull(message = "Code can not be null!")
    private String code;
}
