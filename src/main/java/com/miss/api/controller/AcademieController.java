package com.miss.api.controller;

import com.miss.api.model.Academie;
import com.miss.api.service.AcademieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AcademieController {
    final AcademieService academieService;

    public AcademieController(AcademieService academieService) {
        this.academieService = academieService;
    }

    @PostMapping(value = "/academies/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Academie academie) {
    return academieService.saveAcademie(academie);
    }
    @GetMapping(value = "/academies/liste")
    public ResponseEntity<Map<String, Object>> getAcademies() {
        return academieService.getAllAcademie();
    }
}
