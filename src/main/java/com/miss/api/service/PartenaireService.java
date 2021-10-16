package com.miss.api.service;

import com.miss.api.model.Partenaire;
import com.miss.api.repos.PartenaireRepository;
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
public class PartenaireService {
    final PartenaireRepository partenaireRepository;
    final UploadImageService uploadImageService;
    Map<String, Object> response = new HashMap<>();

    public PartenaireService(PartenaireRepository partenaireRepository, UploadImageService uploadImageService) {
        this.partenaireRepository = partenaireRepository;
        this.uploadImageService = uploadImageService;
    }


    public ResponseEntity<Map<String, Object>> savePartenaire(Partenaire article, MultipartFile photo) {
        try {
            article.setPhoto(uploadImageService.uploadImage(photo));

            Partenaire article1 = partenaireRepository.save(article);
            response.put("message", "Partenaire enregistrée avec succès.");
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

    public ResponseEntity<Map<String, Object>> updateWithPhoto(Partenaire article, MultipartFile photo) {
        try {
            Optional<Partenaire> articleData = partenaireRepository.findById(article.getId());
            article.setPhoto(uploadImageService.updateUploadImage(photo, article.getPhoto()));


            if(articleData.isPresent()) {
                Partenaire article1 = partenaireRepository.save(article);
                response.put("message", "Partenaire mise à jour avec succès.");
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
    public ResponseEntity<Map<String, Object>> updatePartenaire(Partenaire article) {
        try {
            Optional<Partenaire> articleData = partenaireRepository.findById(article.getId());
            if(articleData.isPresent()) {
                partenaireRepository.save(article);
                response.put("message", "Partenaire mise à jour avec succès.");
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

    public ResponseEntity<Map<String, Object>> getAllPartenaire(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Partenaire> articles = partenaireRepository.findAll(paging);
            response.put("message", "Partenaire trouvées");
            response.put("response", articles);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Partenaire());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

