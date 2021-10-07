package com.miss.api.service;

import com.miss.api.model.Participante;
import com.miss.api.model.Suivi3eme8eme;
import com.miss.api.repos.ParticipanteRepository;
import com.miss.api.repos.Suivi3eme6emeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Suivi3eme6emeService {
    final Suivi3eme6emeRepository suivi3eme6emeRepository;
    final ParticipanteRepository participanteRepository;
    Map<String, Object> response = new HashMap<>();

    public Suivi3eme6emeService(Suivi3eme6emeRepository suivi3eme6emeRepository, ParticipanteRepository participanteRepository) {
        this.suivi3eme6emeRepository = suivi3eme6emeRepository;
        this.participanteRepository = participanteRepository;
    }


    public ResponseEntity<Map<String, Object>> saveSuivi3eme8eme(Participante participante) {
        try {

            Suivi3eme8eme suivi3eme8eme = suivi3eme6emeRepository.save(participante.getSuivi3eme6eme());
            Optional<Participante> participanteData = participanteRepository.findById(participante.getId());

            if(participanteData.isPresent()) {

                participante.setSuivi3eme6eme(suivi3eme8eme);
                Participante participante1 = participanteRepository.save(participante);

                response.put("message", "Suivi3eme6eme enregistrée avec succès.");
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
    public ResponseEntity<Map<String, Object>> updateSuivi3eme8eme(Suivi3eme8eme suivi3eme8eme) {
        try {
            Optional<Suivi3eme8eme> suivi3eme8emeData = suivi3eme6emeRepository.findById(suivi3eme8eme.getId());
            if(suivi3eme8emeData.isPresent()) {
                suivi3eme6emeRepository.save(suivi3eme8eme);
                response.put("message", "Suivi3eme8eme enregistrée avec succès.");
                response.put("response", suivi3eme8eme);
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

    public ResponseEntity<Map<String, Object>> getAllSuivi3eme8eme() {
        try {
            List<Suivi3eme8eme> suivi3eme8emes = suivi3eme6emeRepository.findAll();
            response.put("message", "Suivi3eme8eme trouvées");
            response.put("response", suivi3eme8emes);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Suivi3eme8eme());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
