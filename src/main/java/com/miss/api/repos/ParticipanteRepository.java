package com.miss.api.repos;

import com.miss.api.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Page<Participante> findAll(Pageable pageable);
    List<Participante> findByAcademieId(Long academieId);
    List<Participante> findByAnneeId(Long anneeId);
    List<Participante> findAllByPrenom(String prenom);
    List<Participante> findAllByNom(String nom);
    List<Participante> findAllByClasseId(Long classeId);

    //agrege 2 param with prenom
    List<Participante> findAllByPrenomAndNom(String prenom, String nom);
    List<Participante> findAllByPrenomAndAcademieId(String prenom, Long academieId);
    List<Participante> findAllByPrenomAndClasseId(String prenom, Long classeId);
    List<Participante> findAllByPrenomAndAnneeId(String prenom, Long anneeId);

    //agrege 2 param with nom
    List<Participante> findAllByNomAndAcademieId(String nom, Long academieId);
    List<Participante> findAllByNomAndClasseId(String nom, Long classeId);
    List<Participante> findAllByNomAndAnneeId(String nom, Long anneeId);

    //agrege 2 param with academie
    List<Participante> findAllByAcademieIdAndClasseId(Long academieId, Long classeId);
    List<Participante> findAllByAcademieIdAndAnneeId(Long academieId, Long anneeId);

    //agrege 2 param with classe
    List<Participante> findAllByClasseIdAndAnneeId(Long classeId, Long anneeId);

    //agrege 3 param with prenom
    List<Participante> findAllByPrenomAndNomAndAcademieId(String prenom, String nom, Long academieId);
    List<Participante> findAllByPrenomAndNomAndClasseId(String prenom, String nom, Long classeId);
    List<Participante> findAllByPrenomAndNomAndAnneeId(String prenom, String nom, Long anneeId);

    //agrege 3 param with nom
    List<Participante> findAllByNomAndAcademieIdAndClasseId(String nom, Long academieId, Long classeId);
    List<Participante> findAllByNomAndAcademieIdAndAnneeId(String nom, Long academieId, Long anneeId);

    //agrege 3 param with academie
    List<Participante> findAllByAcademieIdAndPrenomAndClasseId(Long academieId, String prenom, Long classeId);
    List<Participante> findAllByAcademieIdAndPrenomAndAnneeId(Long academieId, String prenom, Long anneeId);

    //agrege 3 param with classe
    List<Participante> findAllByClasseIAndPrenomAndAcademieId(Long classeId, String prenom, Long academieId);
    List<Participante> findAllByClasseIAndPrenomAndAnneeId(Long classeId, String prenom, Long anneeId);

}
