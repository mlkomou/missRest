package com.miss.api.controller;
import com.miss.api.model.Participante;
import com.miss.api.service.LaureateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LaureateController {
    final LaureateService laureateService;

    public LaureateController(LaureateService laureateService) {
        this.laureateService = laureateService;
    }

    @PutMapping(value = "/laureates/create")
    public ResponseEntity<Map<String, Object>> saveLaureates(@RequestBody Participante participante) {
        return laureateService.saveLaureate(participante);
    }

    @GetMapping(value = "/laureates/liste/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getParticipantes(@PathVariable int page, @PathVariable int size) {
        return laureateService.getAllLaureate(page, size);
    }
}
