package com.company.milliyuniversity.mapper;

import com.company.milliyuniversity.domains.auth.AuthRole;
import com.company.milliyuniversity.dtos.auth.AuthRoleCreateDTO;
import com.company.milliyuniversity.dtos.auth.AuthRoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthRoleMapper {
    AuthRoleDTO toDTO(AuthRole entity);

    List<AuthRoleDTO> toDTO(List<AuthRole> entities);

    AuthRole fromCreateDTO(AuthRoleCreateDTO dto);
}
