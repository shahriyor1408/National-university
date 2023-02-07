package com.company.milliyuniversity.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 17:45
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleSessionCreateDto {

    @NotBlank(message = "Name can not be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;

    @NotBlank(message = "Conference can not be blank!")
    @NotNull(message = "Conference can not be null!")
    private String conference;
}
