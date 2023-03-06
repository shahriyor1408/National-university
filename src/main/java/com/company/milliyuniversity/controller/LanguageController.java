package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.auth.Language;
import com.company.milliyuniversity.dtos.LanguageCreateDto;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 27/02/23 Monday 14:14
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class LanguageController extends ApiController<LanguageService> {
    protected LanguageController(LanguageService service) {
        super(service);
    }

    @GetMapping(PATH + "/languages")
    public ApiResponse<List<Language>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @PostMapping(PATH + "/language/create")
    public ApiResponse<Long> create(@RequestBody @Valid LanguageCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @PostMapping(PATH + "/language/edit")
    public ApiResponse<Void> editLanguage(@RequestBody String code) {
        service.editLanguage(code);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(PATH + "/language/current")
    public ApiResponse<Language> getCurrentLanguage() {
        return new ApiResponse<>(service.getCurrentLanguage());
    }

    @DeleteMapping(PATH + "/language/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, true);
    }
}
