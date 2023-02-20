package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);

    @Query(value = "from AuthUser where telephone = :telephone")
    Optional<AuthUser> findByTelephone(@Param(value = "telephone") String telephone);

    @Query(value = "from AuthUser where email = :email")
    Optional<AuthUser> findByEmail(String email);

    @Query(value = "from AuthUser order by id desc")
    List<AuthUser> findAllByOrder();
}