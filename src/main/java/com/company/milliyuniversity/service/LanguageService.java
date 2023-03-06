package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.auth.Language;
import com.company.milliyuniversity.dtos.LanguageCreateDto;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.mapper.LanguageMapper;
import com.company.milliyuniversity.repository.LanguageRepository;
import com.company.milliyuniversity.validator.LanguageCheckService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 27/02/23 Monday 14:14
 * milliy-university/IntelliJ IDEA
 */
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageCheckService checkService;
    private final LanguageMapper languageMapper;

    public LanguageService(LanguageRepository languageRepository, LanguageCheckService checkService, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.checkService = checkService;
        this.languageMapper = languageMapper;
    }

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public void editLanguage(@NonNull String code) {
        checkService.checkByCode(code);
        languageRepository.disableLanguages();
        languageRepository.activateByCode(code);
    }

    public Long create(LanguageCreateDto dto) {
        checkService.checkIfExist(dto.getCode());
        Language language = languageMapper.fromCreateDto(dto);
        language.setActive(false);
        return languageRepository.save(language).getId();
    }

    public void delete(@NonNull Long id) {
        checkService.checkById(id);
        languageRepository.deleteById(id);
    }

    public Language getCurrentLanguage() {
        return languageRepository.getCurrent().orElseThrow(() -> {
            throw new GenericNotFoundException("Language not found!", 404);
        });
    }
}
