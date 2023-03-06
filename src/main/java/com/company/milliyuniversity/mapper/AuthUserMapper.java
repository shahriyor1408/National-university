package com.company.milliyuniversity.mapper;

import com.company.milliyuniversity.domains.auth.AuthUser;
import com.company.milliyuniversity.dtos.UserRegisterDTO;
import com.company.milliyuniversity.dtos.auth.AuthUserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {
    AuthUser fromRegisterDTO(UserRegisterDTO dto);

    void update(AuthUserUpdateDto dto, @MappingTarget AuthUser authUser);
}
