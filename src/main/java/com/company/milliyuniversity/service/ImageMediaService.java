package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.ImageMedia;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.ImageMediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 12:45
 * milliy-university/IntelliJ IDEA
 */
@Service
public class ImageMediaService {

    private final StorageService storageService;
    private final ImageMediaRepository imageMediaRepository;

    public ImageMediaService(StorageService storageService, ImageMediaRepository imageMediaRepository) {
        this.storageService = storageService;
        this.imageMediaRepository = imageMediaRepository;
    }

    public void upload(MultipartFile file) {
        imageMediaRepository.save(storageService.uploadPhoto(file));
    }

    public List<ImageMedia> getAll() {
        return imageMediaRepository.findAllByOrder();
    }

    public void delete(Long id) {
        imageMediaRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Media not found!", 404));
        imageMediaRepository.deleteById(id);
    }
}
