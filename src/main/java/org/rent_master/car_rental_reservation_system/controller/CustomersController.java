package org.rent_master.car_rental_reservation_system.controller;

import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    @Autowired
    public CustomersController(RestTemplate restTemplate, UrlConfig urlConfig) {
        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
    }

    // GET : Get All Customers
    @GetMapping("")
    public ResponseEntity<?> readCustomersData() {
        String url = urlConfig.readCustomersUrl();
        System.out.println("Get Customers URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
