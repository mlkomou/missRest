package com.miss.api.service;

import com.miss.api.model.Annee;
import com.miss.api.repos.AnneeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnneeService {
    final AnneeRepository anneeRepository;
    Map<String, Object> response = new HashMap<>();

    public AnneeService(AnneeRepository anneeRepository) {
        this.anneeRepository = anneeRepository;
    }

    public ResponseEntity<Map<String, Object>> saveAnnee(Annee annee) {
        try {
            anneeRepository.save(annee);
            response.put("message", "Année enregistrée avec succès.");
            response.put("response", annee);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'année échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateAnnee(Annee annee) {
        try {
            Optional<Annee> anneeData = anneeRepository.findById(annee.getId());
            if(anneeData.isPresent()) {
                anneeRepository.save(annee);
                response.put("message", "Année enregistrée avec succès.");
                response.put("response", annee);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'année échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllAnnee() {
        try {
            List<Annee> annees = anneeRepository.findAll();
            response.put("message", "Année trouvées");
            response.put("response", annees);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Annee());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
