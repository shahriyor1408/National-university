package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.AppDocumentFile;
import com.company.milliyuniversity.domains.Article;
import com.company.milliyuniversity.dtos.ArticleCreateDto;
import com.company.milliyuniversity.dtos.ArticleDto;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.AppDocumentFileRepository;
import com.company.milliyuniversity.repository.ArticleRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final StorageService storageService;

    private final AppDocumentFileRepository appDocumentFileRepository;

    public ArticleService(ArticleRepository articleRepository, AuthUserService authUserService, ArticleSessionService articleSessionService, StorageService storageService, AppDocumentFileRepository appDocumentFileRepository) {
        this.articleRepository = articleRepository;
        this.authUserService = authUserService;
        this.articleSessionService = articleSessionService;
        this.storageService = storageService;
        this.appDocumentFileRepository = appDocumentFileRepository;
    }

    public Long create(ArticleCreateDto formdata) {
        AppDocumentFile documentFile = storageService.uploadArticleFile(formdata.getFile());
        appDocumentFileRepository.save(documentFile);
        Article article = Article.builder()
                .name(formdata.getName())
                .sessionId(formdata.getSessionId())
                .authUserId(authUserService.getSessionAuthUser().getId())
                .regDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(Article.ArticleStatus.UNDER_CONSIDERATION)
                .filePath(documentFile.getPath())
                .build();
        return articleRepository.save(article).getId();
    }

    public void delete(@NonNull Long id) {
        if (articleRepository.findById(id).isEmpty()) {
            throw new GenericNotFoundException("Article not found!", 404);
        }
        articleRepository.deleteById(id);
    }

    public void check(Long id) {
        if (articleRepository.findById(id).isEmpty()) {
            throw new GenericNotFoundException("Article not found!", 404);
        }
        Article article = articleRepository.findById(id).get();
        article.setStatus(Article.ArticleStatus.CONSIDERED);
        articleRepository.save(article);
    }

    public List<ArticleDto> getAllByUser() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        Optional<List<Article>> optionalArticles = articleRepository.findByUserId(authUserService.getSessionAuthUser().getId());

        if (optionalArticles.isEmpty()) {
            throw new GenericNotFoundException("Articles are not found!", 404);
        }

        List<Article> articleList = optionalArticles.get();
        articleList.forEach(article -> articleDtos.add(ArticleDto.builder()
                .id(article.getId())
                .name(article.getName())
                .status(article.getStatus().name())
                .filePath(article.getFilePath())
                .regDate(article.getRegDate())
                .articleSession(articleSessionService.get(article.getSessionId()))
                .authUser(authUserService.getSessionAuthUser())
                .build()));
        return articleDtos;
    }

    public List<ArticleDto> getAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> all = articleRepository.findAll();
        if (!all.isEmpty()) {
            all.forEach(article -> articleDtos.add(ArticleDto.builder()
                    .id(article.getId())
                    .name(article.getName())
                    .status(article.getStatus().name())
                    .filePath(article.getFilePath())
                    .regDate(article.getRegDate())
                    .articleSession(articleSessionService.get(article.getSessionId()))
                    .authUser(authUserService.getSessionAuthUser())
                    .build()));
        }
        return articleDtos;
    }
}
