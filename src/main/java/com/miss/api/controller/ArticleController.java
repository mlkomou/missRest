package com.miss.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.api.model.Article;
import com.miss.api.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ArticleController {
    final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/articles/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestParam("article") String articleString,
                                                       @RequestParam("photo") MultipartFile photo) throws JsonProcessingException {
        Article article = new ObjectMapper().readValue(articleString, Article.class);
        return articleService.saveArticle(article, photo);
    }
    @PutMapping(value = "/articles/update")
    public ResponseEntity<Map<String, Object>> updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @PutMapping(value = "/articles/updateWithPhoto")
    public ResponseEntity<Map<String, Object>> updateArticleWithPhoto(@RequestParam("article") String articleString,
                                                                              @RequestParam("photo")MultipartFile photo) throws JsonProcessingException {
        Article article = new ObjectMapper().readValue(articleString, Article.class);
        return articleService.updateWithPhoto(article, photo);
    }

    @GetMapping(value = "/articles/liste/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getArticles(@PathVariable int page, @PathVariable int size) {
        return articleService.getAllArticle(page, size);
    }
}
