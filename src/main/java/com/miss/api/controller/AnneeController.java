package com.miss.api.controller;

import com.miss.api.model.Annee;
import com.miss.api.service.AnneeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnneeController {
    final AnneeService anneeService;

    public AnneeController(AnneeService anneeService) {
        this.anneeService = anneeService;
    }

    @PostMapping(value = "/annees/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Annee academie) {
        return anneeService.saveAnnee(academie);
    }
    @GetMapping(value = "/annees/liste")
    public ResponseEntity<Map<String, Object>> getAnnees() {
        return anneeService.getAllAnnee();
    }
}
