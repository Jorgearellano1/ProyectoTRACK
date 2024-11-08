package com.example.trackingbackend1.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrackingResponseDto {

    private long id;
    private String color;
    private Boolean isActive;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<TrackingRegisterResponseDto> registros;

}
