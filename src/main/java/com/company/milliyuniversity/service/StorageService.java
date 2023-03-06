package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.AppDocumentFile;
import com.company.milliyuniversity.domains.ImageMedia;
import com.company.milliyuniversity.exceptions.GenericRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/11/22 Monday 18:39
 * telegram-bot-app/IntelliJ IDEA
 */
@Service
public class StorageService {

    private final String appFile;
    private final String articleFile;
    private final String mediaPath;

    private final String filePath;

    public StorageService(@Value("${app.document.upload.path}") String appFile, @Value("${article.document.upload.path}") String articleFile,
                          @Value("${media.upload.path}") String mediaPath, @Value("${file.path}") String filePath) {
        this.appFile = appFile;
        this.articleFile = articleFile;
        this.mediaPath = mediaPath;
        this.filePath = filePath;
    }

    @PostConstruct
    private void createFile() {
        var appFilePath = Paths.get(appFile);
        if (!Files.exists(appFilePath)) {
            try {
                Files.createDirectory(appFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        var mediaPathPath = Paths.get(mediaPath);
        if (!Files.exists(mediaPathPath)) {
            try {
                Files.createDirectory(mediaPathPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        var articlePath = Paths.get(articleFile);
        if (!Files.exists(articlePath)) {
            try {
                Files.createDirectory(articlePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public AppDocumentFile uploadArticleFile(MultipartFile file) {
        var fileName = UUID.randomUUID() + checkFile(file);
        var dest = Paths.get(articleFile + "/" + fileName);
        try {
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
            return AppDocumentFile
                    .builder()
                    .contentType(file.getContentType())
                    .originalName(fileName)
                    .size(file.getSize())
                    .path("http://localhost:8080/api/v1/article/download/" + fileName)
//                    .path("http://ec2-18-181-189-44.ap-northeast-1.compute.amazonaws.com:8080/api/v1/article/download/" + fileName)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong try again! Check your action!");
        }
    }

    public ImageMedia uploadPhoto(MultipartFile file) {
        var fileName = UUID.randomUUID() + checkFile(file);
        var dest = Paths.get(mediaPath + "/" + fileName);
        try {
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
            return ImageMedia
                    .builder()
                    .contentType(file.getContentType())
                    .originalName(fileName)
                    .size(file.getSize())
                    .path(filePath + dest.toAbsolutePath())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong try again! Check your action!");
        }
    }

    private String checkFile(MultipartFile file) {
        StringBuilder name = new StringBuilder();
        if (file != null) {
            if (file.getContentType() == null) {
                throw new GenericRuntimeException("File content type is not supported!", 400);
            }
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null) {
                for (int i = 0; i < originalFilename.length(); i++) {
                    if (originalFilename.charAt(i) != ' ') {
                        name.append(originalFilename.charAt(i));
                    }
                }
            } else {
                name.append("filename");
            }
        } else {
            throw new GenericRuntimeException("File is null!", 400);
        }
        return name.toString();
    }
}
