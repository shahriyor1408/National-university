package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.ImageMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 12:49
 * milliy-university/IntelliJ IDEA
 */
public interface ImageMediaRepository extends JpaRepository<ImageMedia, Long> {
    @Query(value = "from ImageMedia order by id desc")
    List<ImageMedia> findAllByOrder();
}
