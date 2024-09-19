package org.rent_master.car_rental_reservation_system.controller;

import org.rent_master.car_rental_reservation_system.DTO.CustomerCreationDTO;
import org.rent_master.car_rental_reservation_system.DTO.ReservationCreationDTO;
import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    @Autowired
    public ReservationsController(RestTemplate restTemplate, UrlConfig urlConfig) {
        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
    }


    // POST : Create new Reservation
    @PostMapping("")
    public ResponseEntity<?> createReservation(@RequestBody ReservationCreationDTO reservationCreationDTO) {
        String url = urlConfig.createReservationUrl();
        System.out.println("Create Reservation URL: " + url);

        HttpEntity<ReservationCreationDTO> requestEntity = new HttpEntity<>(reservationCreationDTO);
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : Get all Reservations by Branch of Loan ID
    @GetMapping("/branches/{branchId}")
    public ResponseEntity<?> readBranchReservations(@PathVariable Long branchId) {
        String url = urlConfig.readBranchReservationsUrl(branchId);
        System.out.println("Get Branch Reservations URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : Get all Reservations by Branch of Loan ID
    @GetMapping("/cars/{carId}")
    public ResponseEntity<?> readCarReservations(@PathVariable Long carId) {
        String url = urlConfig.readCarReservationsUrl(carId);
        System.out.println("Get Car Reservations URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : Get all Reservations by Car of Loan ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> readCustomerReservations(@PathVariable Long customerId) {
        String url = urlConfig.readCustomerReservationsUrl(customerId);
        System.out.println("Get Customer Reservations URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // GET : Get car BranchInfo for confirm Booking
    @GetMapping("/rentals/{rentalId}/branches/{branchId}")
    public ResponseEntity<?> readBranchInfo(@PathVariable Long rentalId, @PathVariable Long branchId) {
        String url = urlConfig.readBranchInfoUrl(rentalId, branchId);
        System.out.println("Get Branch Info URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // PUT : Renting Prolongation
    @PutMapping("/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId,
                                                    @RequestBody ReservationCreationDTO reservationCreationDTO) {
        String url = urlConfig.updateReservationUrl(reservationId);
        System.out.println("Update Reservation URL: " + url);

        HttpEntity<ReservationCreationDTO> requestEntity = new HttpEntity<>(reservationCreationDTO);
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // DELETE : Delete Concretely Reservation
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
        String url = urlConfig.deleteReservationUrl(reservationId);
        System.out.println("Delete Reservation URL: " + url);

        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Object.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
