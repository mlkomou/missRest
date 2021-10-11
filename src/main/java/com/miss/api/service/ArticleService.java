package com.miss.api.service;

import com.miss.api.model.Article;
import com.miss.api.repos.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticleService {
    final ArticleRepository articleRepository;
    final UploadImageService uploadImageService;
    Map<String, Object> response = new HashMap<>();

    public ArticleService(ArticleRepository articleRepository, UploadImageService uploadImageService) {
        this.articleRepository = articleRepository;
        this.uploadImageService = uploadImageService;
    }


    public ResponseEntity<Map<String, Object>> saveArticle(Article article, MultipartFile photo) {
        try {
            article.setPhoto(uploadImageService.uploadImage(photo));

            Article article1 = articleRepository.save(article);
            response.put("message", "Article enregistrée avec succès.");
            response.put("response", article1);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> updateWithPhoto(Article article, MultipartFile photo) {
        try {
            Optional<Article> articleData = articleRepository.findById(article.getId());
            article.setPhoto(uploadImageService.updateUploadImage(photo, article.getPhoto()));


            if(articleData.isPresent()) {
                Article article1 = articleRepository.save(article);
                response.put("message", "Article mise à jour avec succès.");
                response.put("response", article1);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateArticle(Article article) {
        try {
            Optional<Article> articleData = articleRepository.findById(article.getId());
            if(articleData.isPresent()) {
                articleRepository.save(article);
                response.put("message", "Article mise à jour avec succès.");
                response.put("response", article);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Modification échouée.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllArticle(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Article> articles = articleRepository.findAll(paging);
            response.put("message", "Article trouvées");
            response.put("response", articles);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Article());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
