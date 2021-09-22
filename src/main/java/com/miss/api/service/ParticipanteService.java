package com.miss.api.service;

import com.miss.api.model.Participante;
import com.miss.api.repos.ParticipanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParticipanteService {

    final ParticipanteRepository participanteRepository;
    final UploadImageService uploadImageService;
    Map<String, Object> response = new HashMap<>();

    public ParticipanteService(ParticipanteRepository participanteRepository, UploadImageService uploadImageService) {
        this.participanteRepository = participanteRepository;
        this.uploadImageService = uploadImageService;
    }

    public ResponseEntity<Map<String, Object>> saveParticipante(Participante participante, MultipartFile photo) {
        try {
            participante.setPhoto(uploadImageService.uploadImage(photo));

            Participante participante1 = participanteRepository.save(participante);
            response.put("message", "Participante enregistrée avec succès.");
            response.put("response", participante1);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateParticipante(Participante participante) {
        try {
            Optional<Participante> participanteData = participanteRepository.findById(participante.getId());
            if(participanteData.isPresent()) {
                participanteRepository.save(participante);
                response.put("message", "Participante enregistrée avec succès.");
                response.put("response", participante);
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

    public ResponseEntity<Map<String, Object>> getAllParticipante() {
        try {
            List<Participante> participantes = participanteRepository.findAll();
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
