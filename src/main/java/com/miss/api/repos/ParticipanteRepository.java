package com.miss.api.repos;

import com.miss.api.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Page<Participante> findAll(Pageable pageable);

    List<Participante> findByAcademieNom(String academieNom);
    List<Participante> findByClasseNom(String academieNom);

    List<Participante> findByAnneeNom(String anneeNom);

    List<Participante> findAllByPrenom(String prenom);

    List<Participante> findAllByNom(String nom);

    List<Participante> findAllByClasseNom(String classeNom);

    //agrege 2 param with prenom
    List<Participante> findAllByPrenomAndNom(String prenom, String nom);

    List<Participante> findAllByPrenomAndAcademieNom(String prenom, String academieNom);

    List<Participante> findAllByPrenomAndClasseNom(String prenom, String classeNom);

    List<Participante> findAllByPrenomAndAnneeNom(String prenom, String anneeNom);

    //agrege 2 param with nom
    List<Participante> findAllByNomAndAcademieNom(String nom, String academieNom);

    List<Participante> findAllByNomAndClasseNom(String nom, String classeNom);

    List<Participante> findAllByNomAndAnneeNom(String nom, String anneeNom);

    //agrege 2 param with academie
    List<Participante> findAllByAcademieNomAndClasseNom(String academieNom, String classeNom);

    List<Participante> findAllByAcademieNomAndAnneeNom(String academieNom, String anneeNom);

    //agrege 2 param with classe
    List<Participante> findAllByClasseNomAndAnneeNom(String classeNom, String anneeNom);

    //agrege 3 param with prenom
    List<Participante> findAllByPrenomAndNomAndAcademieNom(String prenom, String nom, String academieNom);

    List<Participante> findAllByPrenomAndNomAndClasseNom(String prenom, String nom, String classeNom);

    List<Participante> findAllByPrenomAndNomAndAnneeNom(String prenom, String nom, String anneeNom);

    //agrege 3 param with nom
    List<Participante> findAllByNomAndAcademieNomAndClasseNom(String nom, String academieNom, String classeNom);

    List<Participante> findAllByNomAndAcademieNomAndAnneeNom(String nom, String academieNom, String anneeNom);

    //agrege 3 param with academie
    List<Participante> findAllByAcademieNomAndPrenomAndClasseNom(String academieNom, String prenom, String classeNom);

    List<Participante> findAllByAcademieNomAndPrenomAndAnneeNom(String academieNom, String prenom, String anneeNom);

    //agrege 3 param with classe
    List<Participante> findAllByClasseNomAndPrenomAndAcademieNom(String classeNom, String prenom, String academieNom);

    List<Participante> findAllByClasseNomAndPrenomAndAnneeNom(String classeNom, String prenom, String anneeNom);

    @Query("select p from Participante p where p.nom = ?1 and p.prenom = ?2 and p.academie.id = ?3 and p.classe.id = ?4 and p.annee.id = ?5")
    List<Participante> searchByAllParams(String nom, String prenom, String academieNom, String classeNom, String anneeNom);

//        @Query("select p from Participante p where p.nom = ?1 and p.prenom = ?2 and p.annee.nom = ?3 and p.classe.nom = ?4")
    List<Participante> findByNomAndPrenomAndAnneeNomAndClasseNom(String nom, String prenom, String anneeNom, String classeNom);

    List<Participante> findByNomAndPrenomAndAnneeNomAndAcademieNom(String nom, String prenom, String anneeNom, String academieNom);

    List<Participante> findByPrenomAndAnneeNomAndAcademieNomAndClasseNom(String prenom, String anneeNom, String academieNom, String classeNom);

    List<Participante> findByNomAndAnneeNomAndAcademieNomAndClasseNom(String nom, String anneeNom, String academieNom, String classeNom);

    List<Participante> findByAcademieId(Long id);

    Optional<Participante> findByNom(String nom);

    List<Participante> findByAnneeId(Long id);
}
