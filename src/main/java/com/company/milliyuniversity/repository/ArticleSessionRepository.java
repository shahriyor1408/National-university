package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.ArticleSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:01
 * milliy-university/IntelliJ IDEA
 */
public interface ArticleSessionRepository extends JpaRepository<ArticleSession, Long> {
    @Query("from ArticleSession order by id desc")
    List<ArticleSession> findAllByOrder();

    @Query("from ArticleSession where name = :name")
    Optional<ArticleSession> findByName(@Param(value = "name") String name);
}
