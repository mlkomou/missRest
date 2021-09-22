package com.miss.api.controller;

import com.miss.api.model.Ecole;
import com.miss.api.service.EcoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EcoleController {
    final EcoleService ecoleService;

    public EcoleController(EcoleService ecoleService) {
        this.ecoleService = ecoleService;
    }

    @PostMapping(value = "/ecoles/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Ecole ecole) {
        return ecoleService.saveEcole(ecole);
    }
    @GetMapping(value = "/ecoles/liste")
    public ResponseEntity<Map<String, Object>> getEcoles() {
        return ecoleService.getAllEcole();
    }
}
