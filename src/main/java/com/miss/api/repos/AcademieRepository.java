package com.miss.api.repos;

import com.miss.api.model.Academie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademieRepository extends JpaRepository<Academie, Long> {
    Academie findAcademieByNom(String nom);
}
