package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.AppDocument;
import com.company.milliyuniversity.domains.AppDocumentFile;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.AppDocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 13:19
 * milliy-university/IntelliJ IDEA
 */
@Service
public class AppDocumentService {

    private final AppDocumentRepository appDocumentRepository;
    private final StorageService storageService;

    public AppDocumentService(AppDocumentRepository appDocumentRepository, StorageService storageService) {
        this.appDocumentRepository = appDocumentRepository;
        this.storageService = storageService;
    }

    public Long upload(String name, MultipartFile file) {
        AppDocumentFile documentFile = storageService.uploadAppFile(file);
        AppDocument document = AppDocument.builder()
                .name(name)
                .filePath(documentFile.getPath())
                .build();
        return appDocumentRepository.save(document).getId();
    }

    public List<AppDocument> getAll() {
        return appDocumentRepository.findAll();
    }

    public void delete(Long id) {
        Optional<AppDocument> optionalAppDocument = appDocumentRepository.findById(id);
        if (optionalAppDocument.isEmpty()) {
            throw new GenericNotFoundException("Document not found!", 404);
        }
        appDocumentRepository.deleteById(id);
    }
}
