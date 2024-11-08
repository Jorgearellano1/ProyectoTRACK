package com.example.trackingbackend1.repository;

import com.example.trackingbackend1.model.Camara;
import com.example.trackingbackend1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CamaraRepository extends JpaRepository<Camara, Long > {
    List<Camara> findAllByDireccion(String direccion);
}
