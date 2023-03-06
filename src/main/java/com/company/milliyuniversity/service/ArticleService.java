package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.AppDocumentFile;
import com.company.milliyuniversity.domains.Article;
import com.company.milliyuniversity.dtos.ArticleCreateDto;
import com.company.milliyuniversity.dtos.ArticleDto;
import com.company.milliyuniversity.repository.AppDocumentFileRepository;
import com.company.milliyuniversity.repository.ArticleRepository;
import com.company.milliyuniversity.validator.ArticleCheckService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:30
 * milliy-university/IntelliJ IDEA
 */
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AuthUserService authUserService;
    private final ArticleSessionService articleSessionService;
    private final ArticleCheckService checkService;
    private final String fileDownloadPath;
    private final StorageService storageService;
    private final AppDocumentFileRepository appDocumentFileRepository;

    public ArticleService(ArticleRepository articleRepository, AuthUserService authUserService,
                          ArticleSessionService articleSessionService, ArticleCheckService checkService, @Value("${file.download}") String fileDownloadPath,
                          StorageService storageService, AppDocumentFileRepository appDocumentFileRepository) {
        this.articleRepository = articleRepository;
        this.authUserService = authUserService;
        this.articleSessionService = articleSessionService;
        this.checkService = checkService;
        this.fileDownloadPath = fileDownloadPath;
        this.storageService = storageService;
        this.appDocumentFileRepository = appDocumentFileRepository;
    }

    public Long create(ArticleCreateDto dto) {
        checkService.checkByName(dto);
        return articleRepository.save(Article.builder()
                .name(dto.getName())
                .sessionId(dto.getSessionId())
                .authUserId(authUserService.getSessionAuthUser().getId())
                .regDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(Article.ArticleStatus.UNDER_CONSIDERATION)
                .build()).getId();
    }

    public void delete(@NonNull Long id) {
        checkService.checkById(id);
        articleRepository.deleteById(id);
    }

    public void check(Long id) {
        Article article = checkService.checkById(id);
        if (article.getStatus().equals(Article.ArticleStatus.CONSIDERED)) {
            article.setStatus(Article.ArticleStatus.UNDER_CONSIDERATION);
        } else {
            article.setStatus(Article.ArticleStatus.CONSIDERED);
        }
        articleRepository.save(article);
    }

    public List<ArticleDto> getAllByUser() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articleList = articleRepository.findByUserId(authUserService.getSessionAuthUser().getId());
        articleList.forEach(article -> articleDtos.add(ArticleDto.builder()
                .id(article.getId())
                .name(article.getName())
                .status(article.getStatus().name())
                .filePath(article.getFilePath())
                .regDate(article.getRegDate())
                .articleSession(articleSessionService.get(article.getSessionId()))
                .authUser(authUserService.get(article.getAuthUserId()))
                .build()));
        return articleDtos;
    }

    public List<ArticleDto> getAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> all = articleRepository.findAllByDescendingOrder();
        all.forEach(article -> articleDtos.add(ArticleDto.builder()
                .id(article.getId())
                .name(article.getName())
                .status(article.getStatus().name())
                .filePath(article.getFilePath())
                .regDate(article.getRegDate())
                .articleSession(articleSessionService.get(article.getSessionId()))
                .authUser(authUserService.get(article.getAuthUserId()))
                .build()));
        return articleDtos;
    }

    public void upload(Long id, MultipartFile file) {
        Article article = checkService.checkById(id);
        AppDocumentFile documentFile = storageService.uploadArticleFile(file);
        appDocumentFileRepository.save(documentFile);
        article.setFilePath(documentFile.getPath());
        articleRepository.save(article);
    }

    public ResponseEntity<InputStreamResource> download(String fileName) throws FileNotFoundException {
        String filePath = fileDownloadPath + fileName; // replace with your own file path
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
