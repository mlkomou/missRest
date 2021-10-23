package com.miss.api.repos;

import com.miss.api.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Page<Participante> findAll(Pageable pageable);
    List<Participante> findByAcademieId(Long academieId);

}
