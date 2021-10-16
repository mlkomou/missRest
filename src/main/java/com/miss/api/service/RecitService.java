package com.miss.api.service;

import com.miss.api.model.Recit;
import com.miss.api.repos.RecitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecitService {
    final RecitRepository recitRepository;
    Map<String, Object> response = new HashMap<>();

    public RecitService(RecitRepository recitRepository) {
        this.recitRepository = recitRepository;
    }


    public ResponseEntity<Map<String, Object>> saveRecit(Recit recit) {
        try {
            recitRepository.save(recit);
            response.put("message", "Directif enregistrée avec succès.");
            response.put("response", recit);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de directif échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateRecit(Recit recit) {
        try {
            Optional<Recit> recitData = recitRepository.findById(recit.getId());
            if(recitData.isPresent()) {
                recitRepository.save(recit);
                response.put("message", "Directif enregistrée avec succès.");
                response.put("response", recit);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de directif échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllRecit() {
        try {
            List<Recit> recits = recitRepository.findAll();
            response.put("message", "Directif trouvées");
            response.put("response", recits);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Recit());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
