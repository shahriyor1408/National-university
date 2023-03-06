package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.ArticleSession;
import com.company.milliyuniversity.dtos.ArticleSessionCreateDto;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.ArticleSessionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 17:41
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class ArticleSessionController extends ApiController<ArticleSessionService> {
    protected ArticleSessionController(ArticleSessionService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/session/create", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Long> create(@Valid @RequestBody ArticleSessionCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @GetMapping(value = PATH + "/session/getAll", produces = "application/json")
    public ApiResponse<List<ArticleSession>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @DeleteMapping(value = PATH + "/session/delete/{id}", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200,true);
    }
}
