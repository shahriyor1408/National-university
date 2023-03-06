package com.company.milliyuniversity.mapper;

import com.company.milliyuniversity.domains.auth.Language;
import com.company.milliyuniversity.dtos.LanguageCreateDto;
import org.mapstruct.Mapper;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 02/03/23 Thursday 14:21
 * milliy-university/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface LanguageMapper {
    Language fromCreateDto(LanguageCreateDto dto);
}
