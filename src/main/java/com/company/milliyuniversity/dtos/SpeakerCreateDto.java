package com.company.milliyuniversity.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 10:43
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeakerCreateDto {

    @NotBlank(message = "Full name can not be blank!")
    @NotNull(message = "Full name can not be null!")
    private String fullName;

    @NotBlank(message = "Description can not be blank!")
    @NotNull(message = "Description can not be null!")
    private String description;

    @NotBlank(message = "Status can not be blank!")
    @NotNull(message = "Status can not be null!")
    private String status;
}
