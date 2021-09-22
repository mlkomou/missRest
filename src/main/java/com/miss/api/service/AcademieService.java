package com.miss.api.service;

import com.miss.api.model.Academie;
import com.miss.api.repos.AcademieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AcademieService {
    final AcademieRepository academieRepository;
    Map<String, Object> response = new HashMap<>();

    public AcademieService(AcademieRepository academieRepository) {
        this.academieRepository = academieRepository;
    }

    public ResponseEntity<Map<String, Object>> saveAcademie(Academie academie) {
        try {
            academieRepository.save(academie);
            response.put("message", "Académie enregistré avec succès.");
            response.put("response", academie);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'acdémie échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
   public ResponseEntity<Map<String, Object>> updateAcademie(Academie academie) {
        try {
            Optional<Academie> academieData = academieRepository.findById(academie.getId());
            if(academieData.isPresent()) {
                academieRepository.save(academie);
                response.put("message", "Académie enregistré avec succès.");
                response.put("response", academie);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'acdémie échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }

   public ResponseEntity<Map<String, Object>> getAllAcademie() {
        try {
            List<Academie> academies = academieRepository.findAll();
            response.put("message", "Académies trouvées");
            response.put("response", academies);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Academie());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }

}
