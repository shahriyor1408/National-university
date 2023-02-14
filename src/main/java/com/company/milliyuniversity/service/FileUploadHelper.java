//package com.company.milliyuniversity.service;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
///**
// * @author "Sohidjonov Shahriyor"
// * @since 13/02/23 Monday 00:10
// * milliy-university/IntelliJ IDEA
// */
//@Component
//public class FileUploadHelper {
//
//    public final String UPLOAD_DIR = new ClassPathResource("./image").getFile().getAbsolutePath();
//
//    public FileUploadHelper() throws IOException {
//
//    }
//
//    public void uploadFile(MultipartFile file) {
//        try {
//            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
