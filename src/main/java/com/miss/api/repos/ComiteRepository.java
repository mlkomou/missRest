package com.miss.api.repos;

import com.miss.api.model.Comite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComiteRepository extends JpaRepository<Comite, Long> {
    Comite findByUserId(Long userId);
}
