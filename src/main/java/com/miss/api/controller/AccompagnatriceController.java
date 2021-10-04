package com.miss.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.api.model.Accompagnatrice;
import com.miss.api.service.AccompagnatriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class AccompagnatriceController {
    final AccompagnatriceService accompagnatriceService;

    public AccompagnatriceController(AccompagnatriceService accompagnatriceService) {
        this.accompagnatriceService = accompagnatriceService;
    }


    @PostMapping(value = "/accompagnatrices/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestParam("accompagnatrice") String accompagnatriceString,
                                                       @RequestParam("photo") MultipartFile photo) throws JsonProcessingException {
        Accompagnatrice accompagnatrice = new ObjectMapper().readValue(accompagnatriceString, Accompagnatrice.class);
        return accompagnatriceService.saveAccompagnatrice(accompagnatrice, photo);
    }
    @PutMapping(value = "/accompagnatrices/update")
    public ResponseEntity<Map<String, Object>> updateAccompagnatrice(@RequestBody Accompagnatrice accompagnatrice) {
        return accompagnatriceService.updateAccompagnatrice(accompagnatrice);
    }

    @PutMapping(value = "/accompagnatrices/updateWithPhoto")
    public ResponseEntity<Map<String, Object>> updateAccompagnatriceWithPhoto(@RequestParam("accompagnatrice") String accompagnatriceString,
                                                                           @RequestParam("photo")MultipartFile photo) throws JsonProcessingException {
        Accompagnatrice accompagnatrice = new ObjectMapper().readValue(accompagnatriceString, Accompagnatrice.class);
        return accompagnatriceService.updateWithPhoto(accompagnatrice, photo);
    }


    @GetMapping(value = "/accompagnatrices/liste/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getAccompagnatrices(@PathVariable int page, @PathVariable int size) {
        return accompagnatriceService.getAllAccompagnatrice(page, size);
    }
}
