package com.miss.api.controller;

import com.miss.api.model.Classe;
import com.miss.api.service.ClasseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClasseController {
    final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @PostMapping(value = "/classes/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Classe academie) {
        return classeService.saveClasse(academie);
    }
    @GetMapping(value = "/classes/liste")
    public ResponseEntity<Map<String, Object>> getClasses() {
        return classeService.getAllClasse();
    }
}
