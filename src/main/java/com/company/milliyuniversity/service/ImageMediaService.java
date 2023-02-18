package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.ImageMedia;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.ImageMediaRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 12:45
 * milliy-university/IntelliJ IDEA
 */
@Service
public class ImageMediaService {

    private final StorageService storageService;
    private final ImageMediaRepository imageMediaRepository;
    private final Path root = Paths.get("./media");

    public ImageMediaService(StorageService storageService, ImageMediaRepository imageMediaRepository) {
        this.storageService = storageService;
        this.imageMediaRepository = imageMediaRepository;
    }

    public void upload(MultipartFile file) {
        imageMediaRepository.save(storageService.uploadPhoto(file));
    }

    public List<ImageMedia> getAll() {
        return imageMediaRepository.findAll();
    }

    public void delete(Long id) {
        Optional<ImageMedia> optionalImageMedia = imageMediaRepository.findById(id);
        if (optionalImageMedia.isEmpty()) {
            throw new GenericNotFoundException("Media not found!", 404);
        }
        imageMediaRepository.deleteById(id);
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
