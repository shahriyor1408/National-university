package com.company.milliyuniversity.validator;

import com.company.milliyuniversity.domains.Article;
import com.company.milliyuniversity.dtos.ArticleCreateDto;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.repository.ArticleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 26/02/23 Sunday 17:09
 * milliy-university/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class ArticleCheckService {

    private final ArticleRepository articleRepository;

    public void checkByName(@NonNull ArticleCreateDto dto) {
        articleRepository.findByName(dto.getName()).orElseThrow(() -> new GenericNotFoundException("Article already exist!", 400));
    }

    public Article checkById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Article not found!", 404));
    }
}
