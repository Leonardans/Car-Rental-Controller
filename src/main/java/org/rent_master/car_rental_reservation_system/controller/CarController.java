package org.rent_master.car_rental_reservation_system.controller;

import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    @Autowired
    public CarController(RestTemplateBuilder restTemplateBuilder, UrlConfig urlConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.urlConfig = urlConfig;
    }


    // GET : Fetch all Cars
    @GetMapping("")
    public ResponseEntity<?> readCarsData() {
        String url = urlConfig.readCarsUrl();
        System.out.println("Get Cars URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : Fetch Cars Images for Browser
    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> readCarImage(@PathVariable String filename) {
        String url = urlConfig.readCarImageUrl(filename);

        ResponseEntity<Resource> response = restTemplate.getForEntity(url, Resource.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
