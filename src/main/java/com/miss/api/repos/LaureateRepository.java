package com.miss.api.repos;

import com.miss.api.model.Laureate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaureateRepository extends JpaRepository<Laureate, Long> {
}
