package com.example.trackingbackend1.controller;

import com.example.trackingbackend1.dtos.TrackingRequestDto;
import com.example.trackingbackend1.dtos.TrackingResponseDto;
import com.example.trackingbackend1.model.Tracking;
import com.example.trackingbackend1.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/trackings")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;


    @GetMapping
    public List<TrackingResponseDto> getAllTrackings() {
        return trackingService.getAllTrackings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingResponseDto> getTracking(@PathVariable Long id) {
        return ResponseEntity.ok(trackingService.getTrackingById(id));
    }

    @PostMapping
    public ResponseEntity<Tracking> createTracking(@RequestBody TrackingRequestDto tracking) {
        return ResponseEntity.ok(trackingService.createTracking(tracking));
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<TrackingResponseDto> updateTracking(Long id) {
        trackingService.changeStatus(id);
        return  ResponseEntity.noContent().build();
    }


}
