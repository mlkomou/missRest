package com.miss.api.controller;

import com.miss.api.repos.CompteurRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompteurController {
    final CompteurRepository compteurRepository;

    public CompteurController(CompteurRepository compteurRepository) {
        this.compteurRepository = compteurRepository;
    }


}
