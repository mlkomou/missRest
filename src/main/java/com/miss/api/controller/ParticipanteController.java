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
import java.util.Optional;

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
//        ParticipanteSimple participanteSimple = new ObjectMapper().readValue(participanteString, ParticipanteSimple.class);

//        Participante participante = new Participante();
//
//        Optional<Academie> academie = academieRepository.findById(participanteSimple.academieId);
//        Optional<Ecole> ecole = ecoleRepository.findById(participanteSimple.ecoleid);
//        Optional<Classe> classe = classeRepository.findById(participanteSimple.classeId);
//        Optional<Annee> annee = anneeRepository.findById(participanteSimple.anneeId);
//
//        participante.setAcademie(academie.get());
//        participante.setEcole(ecole.get());
//        participante.setClasse(classe.get());
//        participante.setAnnee(annee.get());
//
//        participante.setDateNaissance(participanteSimple.getDateNaissance());
//        participante.setLieuNaissance(participanteSimple.getLieuNaissance());
//        participante.setMail(participanteSimple.getMail());
//        participante.setNom(participanteSimple.getNom());
//        participante.setPrenom(participanteSimple.getPrenom());
//        participante.setTelephone(participanteSimple.getTelephone());
//        participante.setTelephoneTuteur(participanteSimple.getTelephoneTuteur());


        return participanteService.saveParticipante(participante, photo);
    }
    @GetMapping(value = "/participantes/liste")
    public ResponseEntity<Map<String, Object>> getParticipantes() {
        return participanteService.getAllParticipante();
    }
}