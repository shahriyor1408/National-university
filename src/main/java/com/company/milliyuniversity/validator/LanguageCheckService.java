package com.company.milliyuniversity.validator;

import com.company.milliyuniversity.domains.auth.Language;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.exceptions.GenericRuntimeException;
import com.company.milliyuniversity.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 27/02/23 Monday 14:27
 * milliy-university/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class LanguageCheckService {

    private final LanguageRepository languageRepository;

    public Language checkByCode(String code) {
        return languageRepository.findByCode(code).orElseThrow(() -> new GenericNotFoundException("Language not found!", 404));
    }

    public void checkById(Long id) {
        languageRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Language not found!", 404));
    }

    public void checkIfExist(String code) {
        languageRepository.findByCode(code).ifPresent(language -> {
            throw new GenericRuntimeException("Language already exist!", 400);
        });
    }
}
