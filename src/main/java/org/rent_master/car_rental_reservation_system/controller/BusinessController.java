package org.rent_master.car_rental_reservation_system.controller;

import org.rent_master.car_rental_reservation_system.DTO.BranchCreationDTO;
import org.rent_master.car_rental_reservation_system.DTO.CarCreationDTO;
import org.rent_master.car_rental_reservation_system.DTO.EmployeeCreationDTO;
import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.rent_master.car_rental_reservation_system.services.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/rentals")
public class BusinessController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;
    private final ControllerService controllerService;

    @Autowired
    public BusinessController(RestTemplate restTemplate, UrlConfig urlConfig, ControllerService controllerService) {
        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
        this.controllerService = controllerService;
    }


    // GET : Rental Data
    @GetMapping("/{rentalId}")
    public ResponseEntity<?> readRentalData(@PathVariable Long rentalId) {
        String url = urlConfig.readRentalDataUrl(rentalId);
        System.out.println("Get Rental Data URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : Rental Employees Data
    @GetMapping("/{rentalId}/employees")
    public ResponseEntity<?> readRentalEmployeesData(@PathVariable Long rentalId) {
        String url = urlConfig.readRentalDataUrl(rentalId);
        System.out.println("Get Rental Employees Data URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // POST : Create Branch for Rental
    @PostMapping("/{rentalId}/branches")
    public ResponseEntity<?> createBranch(@PathVariable Long rentalId,
                                          @ModelAttribute BranchCreationDTO branchCreationDTO)  {
        String url = urlConfig.createBranchUrl(rentalId);
        System.out.println("Create Branch URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(branchCreationDTO),
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // POST : Create Employee for Branch
    @PostMapping("/{rentalId}/branches/{branchId}/employees")
    public ResponseEntity<?> createEmployee(@ModelAttribute EmployeeCreationDTO employeeCreationDTO,
                                            @PathVariable Long rentalId,
                                            @PathVariable Long branchId) {
        String url = urlConfig.createEmployeeUrl(rentalId, branchId);
        System.out.println("Create Employee URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(employeeCreationDTO),
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // POST : Create Car into rental one branch
    @PostMapping("/{rentalId}/branches/{branchId}/cars")
    public ResponseEntity<?> createCar(@PathVariable("rentalId") Long rentalId,
                                            @PathVariable("branchId") Long branchId,
                                            @ModelAttribute CarCreationDTO carCreationDTO) {
        String url = urlConfig.createCarUrl(rentalId, branchId);
        System.out.println("Create Car URL: " + url);

        // Create Body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body = controllerService.convertFromCarCreationDTO(carCreationDTO);
        } catch (IOException e) {
            System.out.println("Show most go on!");
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        controllerService.deleteTempFiles("car_pictures");
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // DELETE : Delete Branch
    @DeleteMapping("/{rentalId}/branches/{branchId}")
    public ResponseEntity<?> deleteBranch(@PathVariable Long rentalId,
                                                @PathVariable Long branchId) {
        String url = urlConfig.deleteBranchUrl(rentalId, branchId);
        System.out.println("Delete Branch URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // DELETE : Delete Employee
    @DeleteMapping("/{rentalId}/branches/{branchId}/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long rentalId,
                                                 @PathVariable Long branchId,
                                                 @PathVariable Long employeeId) {
        String url = urlConfig.deleteEmployeeUrl(rentalId, branchId, employeeId);
        System.out.println("Delete Employee URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // DELETE : Delete Car from rental branch
    @DeleteMapping("/{rentalId}/branches/{branchId}/cars/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long rentalId,
                                            @PathVariable Long branchId,
                                            @PathVariable Long carId) {
        String url = urlConfig.deleteCarUrl(rentalId, branchId, carId);
        System.out.println("Delete Car URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
