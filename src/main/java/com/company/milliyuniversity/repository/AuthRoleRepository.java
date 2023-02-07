package com.company.milliyuniversity.repository;

import com.company.milliyuniversity.domains.auth.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
}
