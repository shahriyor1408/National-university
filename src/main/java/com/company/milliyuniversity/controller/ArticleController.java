package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.dtos.ArticleSessionCreateDto;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.ArticleCreateDto;
import com.company.milliyuniversity.service.ArticleService;
import org.springframework.web.bind.annotation.*;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:29
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class ArticleController extends ApiController<ArticleService> {
    protected ArticleController(ArticleService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/article/create", produces = "application/json")
    public ApiResponse<Long> create(@RequestBody ArticleCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @DeleteMapping(value = PATH + "/article/delete", produces = "application/json")
    public ApiResponse<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200);
    }
}
