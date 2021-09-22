package com.miss.api.repos;

import com.miss.api.model.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Long> {
}
