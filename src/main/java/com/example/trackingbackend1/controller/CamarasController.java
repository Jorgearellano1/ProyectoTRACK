package com.example.trackingbackend1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RestController
@RequestMapping("/api/camaras")
public class CamarasController {

    @Autowired
    private RestTemplate restTemplate;

    private final String vehicleReIdApiUrl = "http://cloud/reid";

    @PostMapping("/track")
    @PreAuthorize("hasRole('VIGILANCE_1') or hasRole('VIGILANCE_2')")
    public ResponseEntity<?> trackVehicle(@RequestParam("image") MultipartFile image) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", image.getResource());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(vehicleReIdApiUrl, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String trackingResult = response.getBody();
                return ResponseEntity.ok("Resultado del tracking: " + trackingResult);
            } else {
                return ResponseEntity.status(response.getStatusCode()).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error durante el tracking del vehículo: " + e.getMessage());
        }
    }
}



