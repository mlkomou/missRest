package com.miss.api.repos;

import com.miss.api.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Page<Participante> findAll(Pageable pageable);
}
