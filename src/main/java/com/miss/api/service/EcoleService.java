package com.miss.api.service;

import com.miss.api.model.Ecole;
import com.miss.api.repos.EcoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EcoleService {
    final EcoleRepository ecoleRepository;
    Map<String, Object> response = new HashMap<>();

    public EcoleService(EcoleRepository ecoleRepository) {
        this.ecoleRepository = ecoleRepository;
    }

    public ResponseEntity<Map<String, Object>> saveEcole(Ecole ecole) {
        try {
            ecoleRepository.save(ecole);
            response.put("message", "Ecole enregistrée avec succès.");
            response.put("response", ecole);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateEcole(Ecole ecole) {
        try {
            Optional<Ecole> ecoleData = ecoleRepository.findById(ecole.getId());
            if(ecoleData.isPresent()) {
                ecoleRepository.save(ecole);
                response.put("message", "Ecole enregistrée avec succès.");
                response.put("response", ecole);
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

    public ResponseEntity<Map<String, Object>> getAllEcole() {
        try {
            List<Ecole> ecoles = ecoleRepository.findAll();
            response.put("message", "Ecole trouvées");
            response.put("response", ecoles);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Ecole());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
