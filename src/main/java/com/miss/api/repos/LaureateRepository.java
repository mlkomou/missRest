package com.miss.api.repos;

import com.miss.api.model.Laureate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaureateRepository extends JpaRepository<Laureate, Long> {
    Page<Laureate> findAll(Pageable pageable);
}
