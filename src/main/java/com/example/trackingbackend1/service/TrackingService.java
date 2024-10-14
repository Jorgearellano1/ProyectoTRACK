package com.example.trackingbackend1.service;

import com.example.trackingbackend1.model.Tracking;
import com.example.trackingbackend1.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    public List<Tracking> getAllTrackings() {
        return trackingRepository.findAll();
    }

    public List<Tracking> getTrackingsByUserId(Long userId) {
        return trackingRepository.findByUserId(userId);
    }

    public List<Tracking> getTrackingsByLicensePlate(String licensePlate) {
        return trackingRepository.findByLicensePlate(licensePlate);
    }

    public Tracking saveTracking(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    public void deleteTracking(Long trackingId) {
        trackingRepository.deleteById(trackingId);
    }

    public Optional<Tracking> getTrackingById(Long id) {
        return trackingRepository.findById(id);
    }

    public Tracking updateTracking(Long id, Tracking trackingDetails) {
        Optional<Tracking> optionalTracking = trackingRepository.findById(id);
        if (optionalTracking.isPresent()) {
            Tracking tracking = optionalTracking.get();
            tracking.setVehicleType(trackingDetails.getVehicleType());
            tracking.setColor(trackingDetails.getColor());
            tracking.setLicensePlate(trackingDetails.getLicensePlate());
            tracking.setStartTime(trackingDetails.getStartTime());
            tracking.setEndTime(trackingDetails.getEndTime());
            tracking.setStartLocation(trackingDetails.getStartLocation());
            tracking.setEndLocation(trackingDetails.getEndLocation());
            tracking.setVehicleId(trackingDetails.getVehicleId());
            return trackingRepository.save(tracking);
        } else {
            throw new RuntimeException("Tracking no encontrado");
        }
    }
}
