package com.example.trackingbackend1.controller;

import com.example.trackingbackend1.model.Tracking;
import com.example.trackingbackend1.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trackings")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String vehicleReIdApiUrl = "http://cloud/api/reid";

    @GetMapping
    public List<Tracking> getAllTrackings() {
        return trackingService.getAllTrackings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tracking> getTrackingById(@PathVariable Long id) {
        Optional<Tracking> tracking = trackingService.getTrackingById(id);
        if (tracking.isPresent()) {
            return ResponseEntity.ok(tracking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tracking> createTracking(@RequestBody Tracking tracking) {
        try {

            ResponseEntity<String> reidResponse = restTemplate.postForEntity(vehicleReIdApiUrl, tracking, String.class);

            if (reidResponse.getStatusCode().is2xxSuccessful()) {
                String vehicleId = reidResponse.getBody();

                tracking.setVehicleId(vehicleId);
                Tracking newTracking = trackingService.saveTracking(tracking);

                return ResponseEntity.ok(newTracking);
            } else {
                return ResponseEntity.status(reidResponse.getStatusCode()).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tracking> updateTracking(@PathVariable Long id, @RequestBody Tracking trackingDetails) {
        Optional<Tracking> existingTracking = trackingService.getTrackingById(id);
        if (existingTracking.isPresent()) {
            Tracking updatedTracking = trackingService.updateTracking(id, trackingDetails);
            return ResponseEntity.ok(updatedTracking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracking(@PathVariable Long id) {
        Optional<Tracking> existingTracking = trackingService.getTrackingById(id);
        if (existingTracking.isPresent()) {
            trackingService.deleteTracking(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
