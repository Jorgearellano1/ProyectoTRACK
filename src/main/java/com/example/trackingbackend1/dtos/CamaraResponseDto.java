package com.example.trackingbackend1.dtos;

import lombok.Data;

@Data
public class CamaraResponseDto {
    private long id;
    private String direccion;
    private Integer numeroDeCalle;
    private String ip;
    private Boolean estado;

}
