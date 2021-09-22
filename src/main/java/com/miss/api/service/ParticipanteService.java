package com.miss.api.service;

import com.miss.api.model.Participante;
import com.miss.api.repos.ParticipanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
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

    public ResponseEntity<Map<String, Object>> updateWithPhoto(Participante participante, MultipartFile photo) {
        try {
            Optional<Participante> participanteData = participanteRepository.findById(participante.getId());
            participante.setPhoto(uploadImageService.updateUploadImage(photo, participante.getPhoto()));


            if(participanteData.isPresent()) {
                Participante participante1 = participanteRepository.save(participante);
                response.put("message", "Participante mise à jour avec succès.");
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
    public ResponseEntity<Map<String, Object>> updateParticipante(Participante participante) {
        try {
            Optional<Participante> participanteData = participanteRepository.findById(participante.getId());
            if(participanteData.isPresent()) {
                participanteRepository.save(participante);
                response.put("message", "Participante mise à jour avec succès.");
                response.put("response", participante);
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

    public ResponseEntity<Map<String, Object>> getAllParticipante(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Participante> participantes = participanteRepository.findAll(paging);
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
