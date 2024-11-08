package com.example.trackingbackend1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trackings")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    private Boolean IsActive;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    @ManyToOne
    private Tracker tracker;

    @OneToMany(mappedBy = "tracking",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TrackingRegister> trackingRegisters = new ArrayList<>();


    @OneToMany(mappedBy = "tracking",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();




}
