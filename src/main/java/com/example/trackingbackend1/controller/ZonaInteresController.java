package com.example.trackingbackend1.controller;

import com.example.trackingbackend1.dtos.ZonaInteresDTO;
import com.example.trackingbackend1.service.ZonaInteresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zonas-interes")
public class ZonaInteresController {

    @Autowired
    private ZonaInteresService zonaInteresService;

    @PostMapping
    public ResponseEntity<?> createZonaInteres(@RequestBody ZonaInteresDTO zonaInteresDTO) {
        String result = zonaInteresService.createZonaInteres(zonaInteresDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getZonaInteresById(@PathVariable String id) {
        ZonaInteresDTO zonaInteres = zonaInteresService.getZonaInteresById(id);
        if (zonaInteres != null) {
            return ResponseEntity.ok(zonaInteres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateZonaInteres(@PathVariable String id, @RequestBody ZonaInteresDTO zonaInteresDTO) {
        String result = zonaInteresService.updateZonaInteres(id, zonaInteresDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZonaInteres(@PathVariable String id) {
        zonaInteresService.deleteZonaInteres(id);
        return ResponseEntity.noContent().build();
    }
}
