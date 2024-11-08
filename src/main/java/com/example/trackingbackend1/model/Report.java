package com.example.trackingbackend1.model;

import jakarta.persistence.*;



@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tracker tracker;

    @ManyToOne
    private Tracking tracking;

    @ManyToOne
    private Authority authority;

}
