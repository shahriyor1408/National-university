package com.company.milliyuniversity.domains;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 16:58
 * milliy-university/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ArticleSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String conference;

    @Column(nullable = false)
    private Timestamp createdAt;
}
