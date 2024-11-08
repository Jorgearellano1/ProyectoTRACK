package com.example.trackingbackend1.repository;

import com.example.trackingbackend1.model.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long> {

    Tracker findByEmail(String username);
}
