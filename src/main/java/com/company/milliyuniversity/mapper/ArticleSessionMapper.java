package com.company.milliyuniversity.mapper;

import com.company.milliyuniversity.domains.ArticleSession;
import com.company.milliyuniversity.dtos.ArticleSessionCreateDto;
import org.mapstruct.Mapper;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 17:56
 * milliy-university/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface ArticleSessionMapper {

    ArticleSession fromMapper(ArticleSessionCreateDto dto);
}
