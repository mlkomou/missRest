package com.miss.api.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.api.model.*;
import com.miss.api.repos.AcademieRepository;
import com.miss.api.repos.AnneeRepository;
import com.miss.api.repos.ClasseRepository;
import com.miss.api.repos.EcoleRepository;
import com.miss.api.service.ParticipanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ParticipanteController {
    final ParticipanteService participanteService;
    final AcademieRepository academieRepository;
    final EcoleRepository ecoleRepository;
    final ClasseRepository classeRepository;
    final AnneeRepository anneeRepository;

    public ParticipanteController(ParticipanteService participanteService, AcademieRepository academieRepository, EcoleRepository ecoleRepository, ClasseRepository classeRepository, AnneeRepository anneeRepository) {
        this.participanteService = participanteService;
        this.academieRepository = academieRepository;
        this.ecoleRepository = ecoleRepository;
        this.classeRepository = classeRepository;
        this.anneeRepository = anneeRepository;
    }


    @PostMapping(value = "/participantes/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestParam("participante") String participanteString,
                                                       @RequestParam("photo")MultipartFile photo) throws JsonProcessingException {
        Participante participante = new ObjectMapper().readValue(participanteString, Participante.class);
        return participanteService.saveParticipante(participante, photo);
    }
    @PutMapping(value = "/participantes/update")
    public ResponseEntity<Map<String, Object>> updateParticipante(@RequestBody Participante participante) {
        return participanteService.updateParticipante(participante);
    }

    @PutMapping(value = "/participantes/updateWithPhoto")
    public ResponseEntity<Map<String, Object>> updateParticipanteWithPhoto(@RequestParam("participante") String participanteString,
                                                                           @RequestParam("photo")MultipartFile photo) throws JsonProcessingException {
        Participante participante = new ObjectMapper().readValue(participanteString, Participante.class);
        return participanteService.updateWithPhoto(participante, photo);
    }


    @GetMapping(value = "/participantes/liste/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getParticipantes(@PathVariable int page, @PathVariable int size) {
        return participanteService.getAllParticipante(page, size);
    }

    @GetMapping(value = "/participantes/statistiques/academie")
    public ResponseEntity<Map<String, Object>> getStatisqueByAcademie() {
        return participanteService.getParticipanteStatistiqueByAcademie();
    }

    @GetMapping(value = "/participantes/statistiques/age")
    public ResponseEntity<Map<String, Object>> getStatisqueByAge() {
        return participanteService.getParticipanteStatistiqueByAge();
    }

    @GetMapping(value = "/participantes/statistiques/annee")
    public ResponseEntity<Map<String, Object>> getStatisqueByAnnee() {
        return participanteService.getStaByAnnee();
    }
}
