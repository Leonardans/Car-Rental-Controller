package org.rent_master.car_rental_reservation_system.controller;

import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/managers")
public class ManagersController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    @Autowired
    public ManagersController(RestTemplate restTemplate, UrlConfig urlConfig) {
        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
    }

}
