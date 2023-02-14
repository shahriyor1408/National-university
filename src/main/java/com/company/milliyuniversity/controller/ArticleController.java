package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.AppDocumentFile;
import com.company.milliyuniversity.dtos.ArticleCreateDto;
import com.company.milliyuniversity.dtos.ArticleDto;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.AppDocumentFileRepository;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.ArticleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:29
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class ArticleController extends ApiController<ArticleService> {
    protected ArticleController(ArticleService service, AppDocumentFileRepository appDocumentFileRepository, @Value("${file.download}") String fileDownloadPath) {
        super(service);
        this.appDocumentFileRepository = appDocumentFileRepository;
        this.fileDownloadPath = fileDownloadPath;
    }

    private final AppDocumentFileRepository appDocumentFileRepository;
    private final String fileDownloadPath;

    @PostMapping(value = PATH + "/article/create", consumes = MediaType.ALL_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Long> create(@RequestBody ArticleCreateDto formdata) {
        return new ApiResponse<>(service.create(formdata));
    }

    @PostMapping(value = PATH + "/article/check/{id}", consumes = "application/json")
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
    public ApiResponse<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(value = PATH + "/article/download/{fileName}", produces = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public void download(@PathVariable String fileName, HttpServletResponse resp) throws IOException {
        Optional<AppDocumentFile> optionalAppDocumentFile = appDocumentFileRepository.findByPath(fileName);
        if (optionalAppDocumentFile.isEmpty()) {
            throw new GenericNotFoundException("Path not found!", 404);
        }
        AppDocumentFile documentFile = optionalAppDocumentFile.get();
        resp.setContentType(documentFile.getContentType());
        resp.setHeader("Content-disposition", "attachment; filename=" + documentFile.getOriginalName());
        Path path = Paths.get(fileDownloadPath, documentFile.getPath());
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path, outputStream);
    }
}
