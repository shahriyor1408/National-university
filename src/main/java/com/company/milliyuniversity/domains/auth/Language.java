package com.company.milliyuniversity.domains.auth;

import lombok.*;

import javax.persistence.*;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 27/02/23 Monday 13:57
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Builder.Default
    private Boolean active = false;
}
