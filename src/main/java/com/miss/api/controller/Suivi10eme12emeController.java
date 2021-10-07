package com.miss.api.controller;

import com.miss.api.model.Participante;
import com.miss.api.model.Suivi10eme12eme;
import com.miss.api.service.Suivi10eme12emeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Suivi10eme12emeController {
    final Suivi10eme12emeService suivi10eme12emeService;

    public Suivi10eme12emeController(Suivi10eme12emeService suivi10eme12emeService) {
        this.suivi10eme12emeService = suivi10eme12emeService;
    }

    @PostMapping(value = "/suivi10eme12emes/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Participante suivi10eme12eme) {
        return suivi10eme12emeService.saveSuivi10eme12eme(suivi10eme12eme);
    }
    @GetMapping(value = "/suivi10eme12emes/liste")
    public ResponseEntity<Map<String, Object>> getSuivi10eme12emes() {
        return suivi10eme12emeService.getAllSuivi10eme12eme();
    }
}
