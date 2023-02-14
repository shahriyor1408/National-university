package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.ArticleSession;
import com.company.milliyuniversity.dtos.ArticleSessionCreateDto;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.mapper.ArticleSessionMapper;
import com.company.milliyuniversity.repository.ArticleSessionRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 17:43
 * milliy-university/IntelliJ IDEA
 */
@Service
public class ArticleSessionService {

    private final ArticleSessionMapper articleSessionMapper;

    private final ArticleSessionRepository articleSessionRepository;

    public ArticleSessionService(ArticleSessionMapper articleSessionMapper, ArticleSessionRepository articleSessionRepository) {
        this.articleSessionMapper = articleSessionMapper;
        this.articleSessionRepository = articleSessionRepository;
    }

    public Long create(@NonNull ArticleSessionCreateDto dto) {
        ArticleSession articleSession = articleSessionMapper.fromMapper(dto);
        articleSession.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return articleSessionRepository.save(articleSession).getId();
    }

    public List<ArticleSession> getAll() {
        return articleSessionRepository.findAll();
    }

    public void delete(@NonNull Long id) {
        if (articleSessionRepository.findById(id).isEmpty()) {
            throw new GenericNotFoundException("Session not found!", 404);
        }
        articleSessionRepository.deleteById(id);
    }

    public ArticleSession get(@NonNull Long sessionId) {
        Optional<ArticleSession> sessionOptional = articleSessionRepository.findById(sessionId);
        if (sessionOptional.isEmpty()) {
            throw new GenericNotFoundException("Session not found!", 404);
        }
        return sessionOptional.get();
    }
}
