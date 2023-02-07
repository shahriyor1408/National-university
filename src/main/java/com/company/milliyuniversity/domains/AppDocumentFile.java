package com.company.milliyuniversity.domains;

import lombok.*;

import javax.persistence.*;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 17:37
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppDocumentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String contentType;
}
