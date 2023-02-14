package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.AppDocument;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.AppDocumentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 13:18
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class AppDocumentController extends ApiController<AppDocumentService> {
    protected AppDocumentController(AppDocumentService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/appDocument/upload", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Long> upload(@Valid @RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        return new ApiResponse<>(service.upload(name, file));
    }

    @GetMapping(PATH + "/appDocument/getAll")
    public ApiResponse<List<AppDocument>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @DeleteMapping(PATH + "/appDocument/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, true);
    }
}
