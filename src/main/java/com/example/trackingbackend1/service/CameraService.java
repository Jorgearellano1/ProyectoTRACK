package com.example.trackingbackend1.service;

import com.example.trackingbackend1.dtos.CamaraResponseDto;
import com.example.trackingbackend1.dtos.CameraRequestDto;
import com.example.trackingbackend1.dtos.TrackingRequestDto;
import com.example.trackingbackend1.dtos.TrackingResponseDto;
import com.example.trackingbackend1.model.Camara;
import com.example.trackingbackend1.model.Tracking;
import com.example.trackingbackend1.repository.CamaraRepository;
import com.example.trackingbackend1.repository.TrackingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraService {

    @Autowired
    private CamaraRepository camaraRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CamaraResponseDto> getCamerasByUbication(String direccion) {
        List<Camara> camaras = camaraRepository.findAllByDireccion(direccion);

        // Mapear cada Tracking a TrackingResponseDto usando ModelMapper
        return camaras.stream()
                .map(camara -> modelMapper.map(camara, CamaraResponseDto.class))
                .collect(Collectors.toList());
    }


    public List<CamaraResponseDto> getAllCameras() {
        List<Camara> camaras = camaraRepository.findAll();

        // Mapear cada Tracking a TrackingResponseDto usando ModelMapper
        return camaras.stream()
                .map(camara -> modelMapper.map(camara, CamaraResponseDto.class))
                .collect(Collectors.toList());
    }


    public CamaraResponseDto createCamera(CameraRequestDto camaraDto) {
        Camara camara = modelMapper.map(camaraDto, Camara.class);
        Camara savedCamara = camaraRepository.save(camara);
        return modelMapper.map(savedCamara, CamaraResponseDto.class);
    }





}
