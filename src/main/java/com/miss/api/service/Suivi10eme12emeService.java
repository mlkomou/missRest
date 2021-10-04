package com.miss.api.service;

import com.miss.api.model.Suivi10eme12eme;
import com.miss.api.repos.Suivi10eme12emeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Suivi10eme12emeService {
    final Suivi10eme12emeRepository suivi10eme12emeRepository;
    Map<String, Object> response = new HashMap<>();

    public Suivi10eme12emeService(Suivi10eme12emeRepository suivi10eme12emeRepository) {
        this.suivi10eme12emeRepository = suivi10eme12emeRepository;
    }


    public ResponseEntity<Map<String, Object>> saveSuivi10eme12eme(Suivi10eme12eme suivi10eme12eme) {
        try {
            suivi10eme12emeRepository.save(suivi10eme12eme);
            response.put("message", "Suivi10eme12eme enregistrée avec succès.");
            response.put("response", suivi10eme12eme);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateSuivi10eme12eme(Suivi10eme12eme suivi10eme12eme) {
        try {
            Optional<Suivi10eme12eme> suivi10eme12emeData = suivi10eme12emeRepository.findById(suivi10eme12eme.getId());
            if(suivi10eme12emeData.isPresent()) {
                suivi10eme12emeRepository.save(suivi10eme12eme);
                response.put("message", "Suivi10eme12eme enregistrée avec succès.");
                response.put("response", suivi10eme12eme);
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

    public ResponseEntity<Map<String, Object>> getAllSuivi10eme12eme() {
        try {
            List<Suivi10eme12eme> suivi10eme12emes = suivi10eme12emeRepository.findAll();
            response.put("message", "Suivi10eme12eme trouvées");
            response.put("response", suivi10eme12emes);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Suivi10eme12eme());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
