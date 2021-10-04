package com.miss.api.service;

import com.miss.api.model.Accompagnatrice;
import com.miss.api.repos.AccompagnatriceRepository;
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
public class AccompagnatriceService {
    final AccompagnatriceRepository accopagnatriceRepository;
    final UploadImageService uploadImageService;
    Map<String, Object> response = new HashMap<>();

    public AccompagnatriceService(AccompagnatriceRepository accopagnatriceRepository, UploadImageService uploadImageService) {
        this.accopagnatriceRepository = accopagnatriceRepository;
        this.uploadImageService = uploadImageService;
    }

    public ResponseEntity<Map<String, Object>> saveAccompagnatrice(Accompagnatrice accompagnatrice, MultipartFile photo) {
        try {
            accompagnatrice.setPhoto(uploadImageService.uploadImage(photo));

            Accompagnatrice accompagnatrice1 = accopagnatriceRepository.save(accompagnatrice);
            response.put("message", "Accompagnatrice enregistrée avec succès.");
            response.put("response", accompagnatrice1);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> updateWithPhoto(Accompagnatrice accompagnatrice, MultipartFile photo) {
        try {
            Optional<Accompagnatrice> accompagnatriceData = accopagnatriceRepository.findById(accompagnatrice.getId());
            accompagnatrice.setPhoto(uploadImageService.updateUploadImage(photo, accompagnatrice.getPhoto()));


            if(accompagnatriceData.isPresent()) {
                Accompagnatrice accompagnatrice1 = accopagnatriceRepository.save(accompagnatrice);
                response.put("message", "Accompagnatrice mise à jour avec succès.");
                response.put("response", accompagnatrice1);
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
    public ResponseEntity<Map<String, Object>> updateAccompagnatrice(Accompagnatrice accompagnatrice) {
        try {
            Optional<Accompagnatrice> accompagnatriceData = accopagnatriceRepository.findById(accompagnatrice.getId());
            if(accompagnatriceData.isPresent()) {
                accopagnatriceRepository.save(accompagnatrice);
                response.put("message", "Accompagnatrice mise à jour avec succès.");
                response.put("response", accompagnatrice);
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

    public ResponseEntity<Map<String, Object>> getAllAccompagnatrice(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Accompagnatrice> accompagnatrices = accopagnatriceRepository.findAll(paging);
            response.put("message", "Accompagnatrice trouvées");
            response.put("response", accompagnatrices);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Accompagnatrice());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
