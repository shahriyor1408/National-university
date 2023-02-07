package com.company.milliyuniversity.service;

import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.ArticleRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 07/02/23 Tuesday 18:30
 * milliy-university/IntelliJ IDEA
 */
@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public Long create(@NonNull ArticleCreateDto dto) {
        return null;
    }

    public void delete(@NonNull Long id) {
        if (articleRepository.findById(id).isEmpty()) {
            throw new GenericNotFoundException("Article not found!", 404);
        }
        articleRepository.deleteById(id);
    }
}
