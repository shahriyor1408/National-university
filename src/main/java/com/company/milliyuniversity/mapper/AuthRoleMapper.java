package com.company.milliyuniversity.mapper;

import com.company.milliyuniversity.domains.auth.AuthRole;
import com.company.milliyuniversity.dtos.auth.AuthRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRoleMapper {
    AuthRoleDTO toDTO(AuthRole entity);
}
