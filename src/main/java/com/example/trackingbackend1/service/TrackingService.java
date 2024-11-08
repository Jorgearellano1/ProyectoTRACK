package com.example.trackingbackend1.service;
import com.example.trackingbackend1.repository.TrackerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.trackingbackend1.dtos.TrackingRequestDto;
import com.example.trackingbackend1.dtos.TrackingResponseDto;
import com.example.trackingbackend1.model.Tracking;
import com.example.trackingbackend1.repository.TrackingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TrackerRepository trackerRepository;




    public List<TrackingResponseDto> getAllTrackings() {
        List<Tracking> trackings = trackingRepository.findAll();

        // Mapear cada Tracking a TrackingResponseDto usando ModelMapper
        return trackings.stream()
                .map(tracking -> modelMapper.map(tracking, TrackingResponseDto.class))
                .collect(Collectors.toList());
    }


    public void changeStatus(Long id){
        Tracking tracking =   trackingRepository.getTrackingById(id);
        tracking.setIsActive(false);
        trackingRepository.save(tracking);
    }

    public List<TrackingResponseDto> getTrackingsByUserId(Long userId) {
        List<Tracking> trackings = trackingRepository.findByUserId(userId);

        // Mapear cada Tracking a TrackingResponseDto usando ModelMapper
        return trackings.stream()
                .map(tracking -> modelMapper.map(tracking, TrackingResponseDto.class))
                .collect(Collectors.toList());
    }

    public Tracking createTracking(TrackingRequestDto request) {

        Tracking tracking = new Tracking();
        tracking.setColor(request.getColor());
        tracking.setIsActive(true);
        tracking.setStartTime(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        tracking.setTracker(trackerRepository.findByEmail(username));
        return trackingRepository.save(tracking);
    }

    public void deleteTracking(Long trackingId) {
        trackingRepository.deleteById(trackingId);
    }

    public TrackingResponseDto getTrackingById(Long id) {

        Optional<Tracking> trackingOptional = trackingRepository.findById(id);

        if (trackingOptional.isPresent()) {
            Tracking tracking = trackingOptional.get();
            return modelMapper.map(tracking, TrackingResponseDto.class);
        } else {
            return null;
        }
    }






}
