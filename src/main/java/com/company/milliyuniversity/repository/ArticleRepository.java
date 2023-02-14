package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:39
 * milliy-university/IntelliJ IDEA
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("from Article where authUserId = :id")
    Optional<List<Article>> findByUserId(Long id);
}
