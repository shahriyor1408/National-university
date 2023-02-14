package com.company.milliyuniversity.dtos;

import com.company.milliyuniversity.domains.ArticleSession;
import com.company.milliyuniversity.domains.auth.AuthUser;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 08:29
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {

    @NotNull(message = "Id can not be null!")
    private Long id;


    @NotBlank(message = "Name can not be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;

    @NotNull(message = "RegDate can not be null!")
    private Timestamp regDate;

    @NotBlank(message = "Status can not be blank!")
    @NotNull(message = "Status can not be null!")
    private String status;

    @NotNull(message = "Article can not be null!")
    private ArticleSession articleSession;

    @NotNull(message = "AuthUser can not be null!")
    private AuthUser authUser;

    @NotBlank(message = "File path can not be blank!")
    @NotNull(message = "File path can not be null!")
    private String filePath;
}
