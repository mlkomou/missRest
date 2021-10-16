package com.miss.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.api.model.Partenaire;
import com.miss.api.service.PartenaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class PartenaireController {
    final PartenaireService partenaireService;

    public PartenaireController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }


    @PostMapping(value = "/partenaires/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestParam("partenaire") String partenaireString,
                                                       @RequestParam("photo") MultipartFile photo) throws JsonProcessingException {
        Partenaire partenaire = new ObjectMapper().readValue(partenaireString, Partenaire.class);
        return partenaireService.savePartenaire(partenaire, photo);
    }
    @PutMapping(value = "/partenaires/update")
    public ResponseEntity<Map<String, Object>> updatePartenaire(@RequestBody Partenaire partenaire) {
        return partenaireService.updatePartenaire(partenaire);
    }

    @PutMapping(value = "/partenaires/updateWithPhoto")
    public ResponseEntity<Map<String, Object>> updatePartenaireWithPhoto(@RequestParam("partenaire") String partenaireString,
                                                                      @RequestParam("photo")MultipartFile photo) throws JsonProcessingException {
        Partenaire partenaire = new ObjectMapper().readValue(partenaireString, Partenaire.class);
        return partenaireService.updateWithPhoto(partenaire, photo);
    }

    @GetMapping(value = "/partenaires/liste/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getPartenaires(@PathVariable int page, @PathVariable int size) {
        return partenaireService.getAllPartenaire(page, size);
    }
}
