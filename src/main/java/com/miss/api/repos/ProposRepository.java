package com.miss.api.repos;

import com.miss.api.model.Propos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposRepository extends JpaRepository<Propos, Long> {
}
