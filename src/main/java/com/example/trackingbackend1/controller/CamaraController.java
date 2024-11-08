package com.example.trackingbackend1.controller;

import com.example.trackingbackend1.dtos.CamaraResponseDto;
import com.example.trackingbackend1.dtos.CameraRequestDto;
import com.example.trackingbackend1.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cameras")
public class CamaraController {

    @Autowired
    private CameraService cameraService;

    // Endpoint para crear una nueva cámara
    @PostMapping
    public ResponseEntity<CamaraResponseDto> createCamera(@RequestBody CameraRequestDto camaraDto) {
        CamaraResponseDto createdCamera = cameraService.createCamera(camaraDto);
        return ResponseEntity.ok(createdCamera);
    }
    // Endpoint para obtener todas las cámaras
    @GetMapping
    public ResponseEntity<List<CamaraResponseDto>> getAllCameras() {
        List<CamaraResponseDto> cameras = cameraService.getAllCameras();
        return ResponseEntity.ok(cameras);
    }
    @GetMapping("/ubication/{direccion}")
    public ResponseEntity<List<CamaraResponseDto>> getCamerasByUbication(@PathVariable String direccion) {
        List<CamaraResponseDto> cameras = cameraService.getCamerasByUbication(direccion);
        return ResponseEntity.ok(cameras);
    }
}
