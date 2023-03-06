package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.ArticleSession;
import com.company.milliyuniversity.dtos.ArticleSessionCreateDto;
import com.company.milliyuniversity.mapper.ArticleSessionMapper;
import com.company.milliyuniversity.repository.ArticleSessionRepository;
import com.company.milliyuniversity.validator.ArticleSessionCheckService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 17:43
 * milliy-university/IntelliJ IDEA
 */
@Service
public class ArticleSessionService {

    private final ArticleSessionMapper articleSessionMapper;

    private final ArticleSessionRepository articleSessionRepository;
    private final ArticleSessionCheckService articleSessionCheckService;

    public ArticleSessionService(ArticleSessionMapper articleSessionMapper,
                                 ArticleSessionRepository articleSessionRepository, ArticleSessionCheckService articleSessionCheckService) {
        this.articleSessionMapper = articleSessionMapper;
        this.articleSessionRepository = articleSessionRepository;
        this.articleSessionCheckService = articleSessionCheckService;
    }

    public Long create(@NonNull ArticleSessionCreateDto dto) {
        articleSessionCheckService.checkByName(dto.getName());
        ArticleSession articleSession = articleSessionMapper.fromMapper(dto);
        articleSession.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return articleSessionRepository.save(articleSession).getId();
    }

    public List<ArticleSession> getAll() {
        return articleSessionRepository.findAllByOrder();
    }

    public void delete(@NonNull Long id) {
        articleSessionCheckService.checkById(id);
        articleSessionRepository.deleteById(id);
    }

    public ArticleSession get(@NonNull Long sessionId) {
        return articleSessionRepository.findById(sessionId).orElse(null);
    }
}
