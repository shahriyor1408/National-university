package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.dtos.ArticleCreateDto;
import com.company.milliyuniversity.dtos.ArticleDto;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.ArticleService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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

    @PostMapping(value = PATH + "/article/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Long> create(@Valid @RequestBody ArticleCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @PutMapping(value = PATH + "/article/check/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> check(@PathVariable Long id) {
        service.check(id);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(value = PATH + "/article/getAllByUser", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<List<ArticleDto>> getAllByUser() {
        return new ApiResponse<>(service.getAllByUser());
    }

    @GetMapping(value = PATH + "/article/getAll", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ArticleDto>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @DeleteMapping(value = PATH + "/article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, true);
    }

    @PostMapping(value = PATH + "/article/upload/{id}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> upload(@PathVariable Long id, @Valid @RequestParam("file") MultipartFile file) {
        service.upload(id, file);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(PATH + "/article/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {
        return service.download(fileName);
    }
}
