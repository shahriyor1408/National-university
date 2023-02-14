package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.ImageMedia;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.ImageMediaService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(value = PATH + "/media/upload", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> uploadImage(@RequestBody MultipartFile file) {
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

    @GetMapping("/media/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = service.load(filename);
        ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
