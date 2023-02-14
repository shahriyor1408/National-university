package com.company.milliyuniversity.domains;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 16:56
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Timestamp regDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column(nullable = false)
    private Long sessionId;

    @Column(nullable = false)
    private Long authUserId;
    private String filePath;

    public enum ArticleStatus {
        UNDER_CONSIDERATION, CONSIDERED
    }
}
