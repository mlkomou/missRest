package com.miss.api.service;

import com.miss.api.model.Classe;
import com.miss.api.repos.ClasseRepository;
import com.miss.api.repos.ClasseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClasseService {
    final ClasseRepository classeRepository;
    Map<String, Object> response = new HashMap<>();

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public ResponseEntity<Map<String, Object>> saveClasse(Classe annee) {
        try {
            classeRepository.save(annee);
            response.put("message", "Classes enregistrée avec succès.");
            response.put("response", annee);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de classe échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateClasse(Classe annee) {
        try {
            Optional<Classe> anneeData = classeRepository.findById(annee.getId());
            if(anneeData.isPresent()) {
                classeRepository.save(annee);
                response.put("message", "Classes enregistrée avec succès.");
                response.put("response", annee);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de classe échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllClasse() {
        try {
            List<Classe> classes = classeRepository.findAll();
            response.put("message", "Classes trouvées");
            response.put("response", classes);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Classe());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
