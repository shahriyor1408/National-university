package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.AppDocumentFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 16:41
 * milliy-university/IntelliJ IDEA
 */
public interface AppDocumentFileRepository extends JpaRepository<AppDocumentFile, Long> {

    @Query("from AppDocumentFile where originalName like :fileName")
    Optional<AppDocumentFile> findByPath(@Value("fileName") String fileName);
}
