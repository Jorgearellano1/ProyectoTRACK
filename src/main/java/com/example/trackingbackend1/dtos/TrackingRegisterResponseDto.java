package com.example.trackingbackend1.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrackingRegisterResponseDto {
    private Long cameraId;
    private LocalDateTime hora;
}
