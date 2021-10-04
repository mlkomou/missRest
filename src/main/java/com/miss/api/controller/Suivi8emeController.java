package com.miss.api.controller;

import com.miss.api.model.Participante;
import com.miss.api.service.Suivi8emeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Suivi8emeController {
    final Suivi8emeService suivi8emeService;

    public Suivi8emeController(Suivi8emeService suivi8emeService) {
        this.suivi8emeService = suivi8emeService;
    }


    @PostMapping(value = "/suivi8emes/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Participante participante) {
        return suivi8emeService.saveSuivi8eme(participante);
    }
    @GetMapping(value = "/suivi8emes/liste")
    public ResponseEntity<Map<String, Object>> getSuivi8emes() {
        return suivi8emeService.getAllSuivi8eme();
    }
}
