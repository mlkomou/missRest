package com.miss.api.service;

import com.miss.api.model.Participante;
import com.miss.api.model.Suivi8eme;
import com.miss.api.repos.ParticipanteRepository;
import com.miss.api.repos.Suivi8emeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Suivi8emeService {
    final Suivi8emeRepository suivi8emeRepository;
    final ParticipanteRepository participanteRepository;
    Map<String, Object> response = new HashMap<>();

    public Suivi8emeService(Suivi8emeRepository suivi8emeRepository, ParticipanteRepository participanteRepository) {
        this.suivi8emeRepository = suivi8emeRepository;
        this.participanteRepository = participanteRepository;
    }

    public ResponseEntity<Map<String, Object>> saveSuivi8eme(Participante participante) {
        try {
            Suivi8eme suivi8eme = suivi8emeRepository.save(participante.getSuivi8eme());
            Optional<Participante> participanteData = participanteRepository.findById(participante.getId());
            if(participanteData.isPresent()) {

                participante.setSuivi8eme(suivi8eme);
               Participante participante1 = participanteRepository.save(participante);

                response.put("message", "Suivi8eme enregistrée avec succès.");
                response.put("response", participante1);
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
    public ResponseEntity<Map<String, Object>> updateSuivi8eme(Suivi8eme suivi8eme) {
        try {
            Optional<Suivi8eme> suivi8emeData = suivi8emeRepository.findById(suivi8eme.getId());
            if(suivi8emeData.isPresent()) {
                suivi8emeRepository.save(suivi8eme);
                response.put("message", "Suivi8eme enregistrée avec succès.");
                response.put("response", suivi8eme);
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

    public ResponseEntity<Map<String, Object>> getAllSuivi8eme() {
        try {
            List<Suivi8eme> suivi8emes = suivi8emeRepository.findAll();
            response.put("message", "Suivi8eme trouvées");
            response.put("response", suivi8emes);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Suivi8eme());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
