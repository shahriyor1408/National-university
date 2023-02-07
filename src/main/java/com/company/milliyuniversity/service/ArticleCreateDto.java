package com.company.milliyuniversity.service;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:32
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCreateDto {

    @NotBlank(message = "Name can not be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;
}
