package com.example.trackingbackend1.service;

import com.example.trackingbackend1.dtos.ZonaInteresDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZonaInteresService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String cloudApiBaseUrl = "https://api-cloud-geolocation.com/zonas-interes";

    public String createZonaInteres(ZonaInteresDTO zonaInteresDTO) {
        return restTemplate.postForObject(cloudApiBaseUrl, zonaInteresDTO, String.class);
    }

    public ZonaInteresDTO getZonaInteresById(String id) {
        return restTemplate.getForObject(cloudApiBaseUrl + "/" + id, ZonaInteresDTO.class);
    }

    public String updateZonaInteres(String id, ZonaInteresDTO zonaInteresDTO) {
        restTemplate.put(cloudApiBaseUrl + "/" + id, zonaInteresDTO);
        return "Zona de interés actualizada con éxito";
    }

    public void deleteZonaInteres(String id) {
        restTemplate.delete(cloudApiBaseUrl + "/" + id);
    }
}
