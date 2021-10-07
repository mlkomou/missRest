package com.miss.api.controller;

import com.miss.api.model.Participante;
import com.miss.api.model.Suivi3eme8eme;
import com.miss.api.service.Suivi3eme6emeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Suivi3eme6emeController {
    final Suivi3eme6emeService suivi3eme6emeService;

    public Suivi3eme6emeController(Suivi3eme6emeService suivi3eme6emeService) {
        this.suivi3eme6emeService = suivi3eme6emeService;
    }


    @PostMapping(value = "/suivi3eme6emes/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Participante suivi3eme6eme) {
        return suivi3eme6emeService.saveSuivi3eme8eme(suivi3eme6eme);
    }
    @GetMapping(value = "/suivi3eme6emes/liste")
    public ResponseEntity<Map<String, Object>> getSuivi3eme8emes() {
        return suivi3eme6emeService.getAllSuivi3eme8eme();
    }
}
