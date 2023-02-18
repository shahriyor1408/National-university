package com.company.milliyuniversity.controller;

import com.company.milliyuniversity.domains.Speakers;
import com.company.milliyuniversity.dtos.SpeakerCreateDto;
import com.company.milliyuniversity.response.ApiResponse;
import com.company.milliyuniversity.service.SpeakerService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 10:40
 * milliy-university/IntelliJ IDEA
 */
@RestController
public class SpeakerController extends ApiController<SpeakerService> {
    protected SpeakerController(SpeakerService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/speaker/create", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Long> create(@RequestBody SpeakerCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @DeleteMapping(value = PATH + "/speaker/delete/{id}", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(value = PATH + "/speaker/getAll", produces = "application/json")
    public ApiResponse<List<Speakers>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @GetMapping(value = PATH + "/speaker/getAllInvited", produces = "application/json")
    public ApiResponse<List<Speakers>> getAllInvited() {
        return new ApiResponse<>(service.getAllInvited());
    }

    @PostMapping(value = PATH + "/speaker/uploadPhoto/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        service.uploadPhoto(file, id);
        return new ApiResponse<>(200, true);
    }

    @GetMapping(value = "/media", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void getImage(@RequestParam(name = "file") String img, HttpServletResponse resp) {
        ServletOutputStream outputStream;
        try {
            outputStream = resp.getOutputStream();
            Path path = Path.of(img);
            Files.copy(path, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
