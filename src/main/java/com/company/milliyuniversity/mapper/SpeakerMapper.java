package com.company.milliyuniversity.mapper;

import com.company.milliyuniversity.domains.Speakers;
import com.company.milliyuniversity.dtos.SpeakerCreateDto;
import org.mapstruct.Mapper;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 26/02/23 Sunday 19:12
 * milliy-university/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface SpeakerMapper {

    Speakers fromDto(SpeakerCreateDto dto);
}
