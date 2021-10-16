package com.miss.api.controller;

import com.miss.api.model.Recit;
import com.miss.api.service.RecitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class RecitController {
    final RecitService recitService;

    public RecitController(RecitService recitService) {
        this.recitService = recitService;
    }


    @PostMapping(value = "/recits/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Recit academie) {
        return recitService.saveRecit(academie);
    }
    @PutMapping(value = "/recits/update")
    public ResponseEntity<Map<String, Object>> updateRecit(@RequestBody Recit recit) {
        return recitService.updateRecit(recit);
    }
    @GetMapping(value = "/recits/liste")
    public ResponseEntity<Map<String, Object>> getRecits() {
        return recitService.getAllRecit();
    }
}
