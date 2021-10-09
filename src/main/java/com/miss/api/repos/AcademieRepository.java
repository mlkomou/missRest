package com.miss.api.repos;

import com.miss.api.model.Academie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AcademieRepository extends JpaRepository<Academie, Long> {
//    @Query("select ac from Academie ac where ac.nom = ?1")
    Optional<Academie> getAcademieByNom(String nom);
}
