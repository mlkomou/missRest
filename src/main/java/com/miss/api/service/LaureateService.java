package com.miss.api.service;

import com.miss.api.model.Laureate;
import com.miss.api.model.Participante;
import com.miss.api.repos.LaureateRepository;
import com.miss.api.repos.ParticipanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LaureateService {
    final ParticipanteRepository participanteRepository;
    final LaureateRepository laureateRepository;
    Map<String, Object> response = new HashMap<>();

    public LaureateService(ParticipanteRepository participanteRepository, LaureateRepository laureateRepository) {
        this.participanteRepository = participanteRepository;
        this.laureateRepository = laureateRepository;
    }

    public ResponseEntity<Map<String, Object>> saveLaureate(Participante participante) {
        try {
            Optional<Participante> participanteData = participanteRepository.findById(participante.getId());
            if(participanteData.isPresent()) {
                participanteData.get().setIsLaureate("OUI");
                Laureate laureate = new Laureate();
                laureate.setCreatedBy(1);
                laureate.setDateCreation(new Date().toString());
                laureate.setDateModification(new Date().toString());
                laureate.setSupprime(false);
                laureate.setParticipant(participanteData.get());
                laureateRepository.save(laureate);
                participanteRepository.save(participanteData.get());
                response.put("message", "Participante mise à jour avec succès.");
                response.put("response", laureate);
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

    public ResponseEntity<Map<String, Object>> getAllLaureate(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Laureate> participantes = laureateRepository.findAll(paging);
            response.put("message", "Participante trouvées");
            response.put("response", participantes);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Participante());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
