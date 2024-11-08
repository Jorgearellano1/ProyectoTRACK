package com.example.trackingbackend1.dtos;


import lombok.Data;

@Data
public class CameraRequestDto {
    private String direccion;
    private String numeroDeCalle;
    private String ip;
}
