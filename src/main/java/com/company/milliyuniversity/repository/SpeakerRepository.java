package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.Speakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 10:47
 * milliy-university/IntelliJ IDEA
 */
public interface SpeakerRepository extends JpaRepository<Speakers, Long> {

    @Query("from Speakers where fullName = :fullName order by id desc")
    Optional<Speakers> findByFullName(String fullName);

    @Query("from Speakers where status = 'INVITED' order by id desc")
    List<Speakers> getAllInvited();

    @Query("from Speakers order by id desc")
    List<Speakers> findAllByOrder();
}
