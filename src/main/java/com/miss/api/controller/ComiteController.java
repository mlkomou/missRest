package com.miss.api.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.api.model.Comite;
import com.miss.api.service.ComiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ComiteController {
    final ComiteService comiteService;

    public ComiteController(ComiteService comiteService) {
        this.comiteService = comiteService;
    }


    @PostMapping(value = "/comites/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestParam("comite") String comiteString,
                                                       @RequestParam("photo") MultipartFile photo) throws JsonProcessingException {
        Comite comite = new ObjectMapper().readValue(comiteString, Comite.class);
        return comiteService.saveComite(comite, photo);
    }
    @PutMapping(value = "/comites/update")
    public ResponseEntity<Map<String, Object>> updateComite(@RequestBody Comite comite) {
        return comiteService.updateComite(comite);
    }

    @PutMapping(value = "/comites/updateWithPhoto")
    public ResponseEntity<Map<String, Object>> updateComiteWithPhoto(@RequestParam("comite") String comiteString,
                                                                              @RequestParam("photo")MultipartFile photo) throws JsonProcessingException {
        Comite comite = new ObjectMapper().readValue(comiteString, Comite.class);
        return comiteService.updateWithPhoto(comite, photo);
    }


    @GetMapping(value = "/comites/liste/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getComites(@PathVariable int page, @PathVariable int size) {
        return comiteService.getAllComite(page, size);
    }

    @GetMapping(value = "/comites/user/{userId}")
    public ResponseEntity<Map<String, Object>> getCurrentUserComite(@PathVariable Long userId) {
        return comiteService.getCurrentUser(userId);
    }
}
