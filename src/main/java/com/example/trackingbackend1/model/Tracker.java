package com.example.trackingbackend1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Tracker extends User {

    @OneToMany(mappedBy = "tracker",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Tracking> trackings = new ArrayList<>();

    @OneToMany(mappedBy = "tracker",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

}
