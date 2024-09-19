package org.rent_master.car_rental_reservation_system.controller;

import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/rental-partners")
public class RentalPartnersController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    public RentalPartnersController(RestTemplate restTemplate, UrlConfig urlConfig) {
        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
    }


    // GET : Get all partners logos for slider
    @GetMapping("/logos")
    public ResponseEntity<Resource> readARentalsLogos() {
        String url = urlConfig.readAllRentalPartnersLogosUrl();

        ResponseEntity<Resource> response = restTemplate.getForEntity(url, Resource.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : For Browser Logo fetching
    @GetMapping("/logos/{filename:.+}")
    public ResponseEntity<Resource> readRentalLogo(@PathVariable String filename) {
        String url = urlConfig.readRentalPartnerLogoUrl(filename);

        ResponseEntity<Resource> response = restTemplate.getForEntity(url, Resource.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
