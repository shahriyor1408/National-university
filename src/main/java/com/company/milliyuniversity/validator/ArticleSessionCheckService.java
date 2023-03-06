package com.company.milliyuniversity.validator;

import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.ArticleSessionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 26/02/23 Sunday 18:58
 * milliy-university/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class ArticleSessionCheckService {
    private final ArticleSessionRepository articleSessionRepository;

    public void checkByName(@NonNull String name) {
        articleSessionRepository.findByName(name).ifPresent(articleSession -> {
            throw new GenericNotFoundException("Session already exist!", 400);
        });
    }

    public void checkById(@NonNull Long id) {
        articleSessionRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Session not found!", 404);
        });
    }
}
