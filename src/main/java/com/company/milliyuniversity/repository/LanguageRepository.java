package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.auth.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 27/02/23 Monday 14:00
 * milliy-university/IntelliJ IDEA
 */
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query(value = "from Language where code = :code")
    Optional<Language> findByCode(@Param(value = "code") String code);


    @Modifying
    @Query(value = "update Language set active = false where active = true")
    void disableLanguages();

    @Modifying
    @Query(value = "update Language set active = true where code = :code")
    void activateByCode(@Param(value = "code") String code);

    @Query(value = "from Language where active = true")
    Optional<Language> getCurrent();
}
