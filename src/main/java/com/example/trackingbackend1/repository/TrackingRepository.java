package com.example.trackingbackend1.repository;

import com.example.trackingbackend1.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    List<Tracking> findByUserId(Long userId);

    Tracking getTrackingById(Long id);
}
