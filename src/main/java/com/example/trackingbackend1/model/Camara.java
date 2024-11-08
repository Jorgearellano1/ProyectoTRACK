package com.example.trackingbackend1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Camara {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String direccion;

    private Integer numeroDeCalle;

    private String ip;

    private Boolean estado;

    @OneToMany(mappedBy = "camara",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TrackingRegister> trackingRegisters = new ArrayList<>();


}
