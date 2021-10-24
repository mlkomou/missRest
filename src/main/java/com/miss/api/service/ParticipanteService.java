package com.miss.api.service;

import com.miss.api.model.*;
import com.miss.api.repos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ParticipanteService {

    final ParticipanteRepository participanteRepository;
    final UploadImageService uploadImageService;
    final CompteurService compteurService;
    final CompteurRepository compteurRepository;
    final EcoleRepository ecoleRepository;
    final AcademieRepository academieRepository;
    final AnneeRepository anneeRepository;
    Map<String, Object> response = new HashMap<>();

    public ParticipanteService(ParticipanteRepository participanteRepository, UploadImageService uploadImageService, CompteurService compteurService, CompteurRepository compteurRepository, EcoleRepository ecoleRepository, AcademieRepository academieRepository, AnneeRepository anneeRepository) {
        this.participanteRepository = participanteRepository;
        this.uploadImageService = uploadImageService;
        this.compteurService = compteurService;
        this.compteurRepository = compteurRepository;
        this.ecoleRepository = ecoleRepository;
        this.academieRepository = academieRepository;
        this.anneeRepository = anneeRepository;
    }

    public ResponseEntity<Map<String, Object>> saveParticipante(Participante participante, MultipartFile photo) {
        Integer maxNumber = compteurService.getNumberMax();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if(maxNumber.toString().length() == 1) {
            String matricule = "MISS_SCIENCES"+year+"MA"+"00"+maxNumber.toString();
            participante.setMatricule(matricule);
        }

        if(maxNumber.toString().length() == 2) {
            String matricule = "MISS_SCIENCES"+year+"MA"+"0"+maxNumber.toString();
            participante.setMatricule(matricule);
        }

        if(maxNumber.toString().length() == 3) {
            String matricule = "MISS_SCIENCES"+year+"MA"+maxNumber.toString();
            participante.setMatricule(matricule);
        };
        Compteur compteur = new Compteur();
        compteur.setNombre(maxNumber);

        Ecole ecole = ecoleRepository.findByNom(participante.getEcole().getNom());
        if (ecole == null) {
          Ecole ecole1 = ecoleRepository.save(participante.getEcole());
          participante.setEcole(ecole1);
            System.out.println("ecole not exist");
        } else {
            participante.setEcole(ecole);
            System.out.println("ecole exist");
        }
        compteurRepository.save(compteur);

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
        Ecole ecole = ecoleRepository.findByNom(participante.getEcole().getNom());
        if (ecole == null) {
            Ecole ecole1 = ecoleRepository.save(participante.getEcole());
            participante.setEcole(ecole1);
            System.out.println("ecole not exist");
        } else {
            participante.setEcole(ecole);
            System.out.println("ecole exist");
        }
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
        Ecole ecole = ecoleRepository.findByNom(participante.getEcole().getNom());
        if (ecole == null) {
            Ecole ecole1 = ecoleRepository.save(participante.getEcole());
            participante.setEcole(ecole1);
            System.out.println("ecole not exist");
        } else {
            participante.setEcole(ecole);
            System.out.println("ecole exist");
        }
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

    public ResponseEntity<Map<String, Object>> getParticipanteStatistiqueByAcademie() {
        try {
            List<Academie> academies = academieRepository.findAll();
            ArrayList participanteByAcademie = new ArrayList();
            Map<String, Object> respToReturn = new HashMap<>();

            for (Academie academie : academies) {
                List<Participante> participantes = participanteRepository.findByAcademieId(academie.id);
                if (participantes.size() > 0) {
                    response.put("name", academie.getNom());
                    response.put("drilldown", academie.getNom());
                    response.put("y", participantes.size());
                    participanteByAcademie.add(response);
                    response = new HashMap<>();
                }
            }

            respToReturn.put("response", participanteByAcademie);
            respToReturn.put("code", 100);
            respToReturn.put("message", "Statistiques par académie");
            return new ResponseEntity<>(respToReturn, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Participante());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getParticipanteStatistiqueByAge() {
        Map<String, Object> generalRespReturn = new HashMap<>();
        List<Map<String, Object>> participantesGeneralArr = new ArrayList<>();

        try {
            List<Participante> participantes7a12 = new ArrayList<>();
            List<Participante> participantes13a18 = new ArrayList<>();
            List<Participante> participantes19a24 = new ArrayList<>();
            List<Participante> participantes25a30 = new ArrayList<>();

            Map<String, Object> respTo7a12Return = new HashMap<>();
            Map<String, Object> respTo13a18Return = new HashMap<>();
            Map<String, Object> respTo19a24Return = new HashMap<>();
            Map<String, Object> respTo25a30Return = new HashMap<>();


            List<Participante> participantes = participanteRepository.findAll();
            System.out.println("first part "+participantes.get(0).getDateNaissance());

            LocalDateTime ldt = LocalDateTime.now();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date today = sdf.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));
            System.out.println("Second date "+today);
            System.out.println("total participante "+participantes.size());


            for (int i = 0; i < participantes.size(); i++) {
                Date participanteDate = sdf.parse(participantes.get(i).getDateNaissance().toString());
                long diffInMillies = Math.abs(today.getTime() - participanteDate.getTime());
                long days = TimeUnit.MILLISECONDS.toDays(diffInMillies);
//                System.out.println("age " + participantes.get(i).getPrenom() + " " +days);
                long years = days / 365;
                System.out.println("nombre annee "+ " " + i + participantes.get(i).getPrenom() + " " +years);


                if (years >= 7 && years <= 12) {
                    participantes7a12.add(participantes.get(i));
                };
                if (years >= 13 && years <= 18) {
                    participantes13a18.add(participantes.get(i));
                };
                if (years >= 19 && years <= 24) {
                    participantes19a24.add(participantes.get(i));
                };
                if (years >= 25 && years <= 30) {
                    participantes25a30.add(participantes.get(i));
                };
            }

            respTo7a12Return.put("name", "7 à 12");
            respTo7a12Return.put("drilldown", "7 à 12");
            respTo7a12Return.put("y", ((double)participantes7a12.size() *100/(double) participantes.size()));

            respTo13a18Return.put("name", "13 à 18");
            respTo13a18Return.put("drilldown", "13 à 18");
            respTo13a18Return.put("y", (double)participantes13a18.size() *100/(double) participantes.size());

            respTo19a24Return.put("name", "19 à 24");
            respTo19a24Return.put("drilldown", "19 à 24");
            respTo19a24Return.put("y", (double)participantes19a24.size() *100/(double) participantes.size());

            respTo25a30Return.put("name", "25 à 30");
            respTo25a30Return.put("drilldown", "25 à 30");
            respTo25a30Return.put("y", (double)participantes25a30.size() *100/(double) participantes.size());

            participantesGeneralArr.add(respTo7a12Return);
            participantesGeneralArr.add(respTo13a18Return);
            participantesGeneralArr.add(respTo19a24Return);
            participantesGeneralArr.add(respTo25a30Return);

            generalRespReturn.put("total7a12", participantes7a12.size());
            generalRespReturn.put("total13a18", participantes13a18.size());
            generalRespReturn.put("total19a24", participantes19a24.size());
            generalRespReturn.put("total25a30", participantes25a30.size());
            generalRespReturn.put("total", participantes.size());
            generalRespReturn.put("response", participantesGeneralArr);
            generalRespReturn.put("code", 100);
            generalRespReturn.put("message", "Statistique participantes par age");

            return new ResponseEntity<>(generalRespReturn, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Participante());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getStaByAnnee() {
        Map<String, Object> respAnnee = new HashMap<>();
        Map<String, Object> respAnneeToReturn = new HashMap<>();
        ArrayList partArr = new ArrayList();

        try {
            List<Annee> annees = anneeRepository.findAll();
            for (int i=0; i<annees.size(); i++) {
                List<Participante> participantes = participanteRepository.findByAnneeId(annees.get(i).id);
                respAnnee.put("name", annees.get(i).getNom());
                respAnnee.put("y", participantes.size());
                partArr.add(respAnnee);
                respAnnee = new HashMap<>();
            }
            respAnneeToReturn.put("response", partArr);
            respAnneeToReturn.put("message", "Statistiques par année");
            respAnneeToReturn.put("code", 100);

            return new ResponseEntity<>(respAnneeToReturn, HttpStatus.OK);
        } catch (Exception e) {
            respAnneeToReturn.put("message", "Erreur de recupération.");
            respAnneeToReturn.put("response", new Participante());
            respAnneeToReturn.put("code", 200);
            return new ResponseEntity<>(respAnneeToReturn, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
