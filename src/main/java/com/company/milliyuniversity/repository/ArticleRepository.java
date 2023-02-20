package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:39
 * milliy-university/IntelliJ IDEA
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("from Article where authUserId = :id order by id desc")
    Optional<List<Article>> findByUserId(Long id);

    @Query("from Article order by id desc")
    List<Article> findAllByDescendingOrder();

    @Query("from Article where name = :name")
    Optional<Article> findByName(@Param(value = "name") String name);
}
