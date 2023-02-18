package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.ImageMedia;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.ImageMediaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 12:44
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class ImageMediaController extends ApiController<ImageMediaService> {
    protected ImageMediaController(ImageMediaService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/media/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> uploadImage(@RequestParam("file") MultipartFile file) {
        service.upload(file);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(value = PATH + "/media/getAll", produces = "application/json")
    public ApiResponse<List<ImageMedia>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @DeleteMapping(PATH + "/media/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, true);
    }
}
