package org.rent_master.car_rental_reservation_system.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConfig {

    // URL : Model Server URL
    @Value("${server.model.url}")
    private String modelServerUrl;

    /// Cars Controller
    private final String carsController = "/api/cars";
    // Read Cars URL
    public String readCarsUrl() {
        return modelServerUrl + carsController;
    }

    // Read Car Image URL
    public String readCarImageUrl(String fileName) {
        return modelServerUrl + carsController + "/images/" + fileName;
    }


    /// Business Controller
    private final String businessController = "/api/rentals";
    // Read Rental URL
    public String readRentalDataUrl(Long rentalId) {
        return modelServerUrl + businessController + "/" + rentalId;
    }

    // Read Rental URL
    public String readRentalEmployeesDataUrl(Long rentalId) {
        return modelServerUrl + businessController + "/" + rentalId + "/employees";
    }

    // Create Rental URL
    public String createRentalUrl() {
        return modelServerUrl + businessController + "/sign-up";
    }

    // Create Branch URL
    public String createBranchUrl(Long rentalId) {
        return modelServerUrl + businessController + "/" + rentalId + "/branches";
    }

    // Create Employee URL
    public String createEmployeeUrl(Long rentalId, Long branchId) {
        return modelServerUrl + businessController + "/" + rentalId + "/branches/" + branchId + "/employees";
    }

    // Create Car URL
    public String createCarUrl(Long rentalId, Long branchId) {
        return modelServerUrl + businessController + "/" + rentalId + "/branches/" + branchId + "/cars";
    }

    // Delete Branch URL
    public String deleteBranchUrl(Long rentalId, Long branchId) {
        return modelServerUrl + businessController + "/" + rentalId + "/branches/" + branchId;
    }

    // Delete Employee URL
    public String deleteEmployeeUrl(Long rentalId, Long branchId, Long employeeId) {
        return modelServerUrl + businessController + "/" + rentalId + "/branches/" + branchId + "/employees/" + employeeId;
    }

    // Delete Car URL
    public String deleteCarUrl(Long rentalId, Long branchId, Long carId) {
        return modelServerUrl + businessController + "/" + rentalId + "/branches/" + branchId + "/cars/" + carId;
    }


    /// Customers Controller
    private final String customersController = "/api/customers";
    // Read Customers URL
    public String readCustomersUrl() {
        return modelServerUrl + customersController;
    }

    // Create Customer URL
    public String createCustomerUrl() {
        return modelServerUrl + customersController + "/sign-up";
    }


    /// Managers Controller
    private final String managersController = "/api/managers";


    /// Rental Partners Controller
    private final String rentalPartnersController = "/api/rental-partners";
    // Read all Partners Logos URL
    public String readAllRentalPartnersLogosUrl() {
        return modelServerUrl + rentalPartnersController + "/logos";
    }

    // Read Logo URL
    public String readRentalPartnerLogoUrl(String fileName) {
        return modelServerUrl + rentalPartnersController + "/logos/" + fileName;
    }


    /// Reservations Controller
    private final String reservationsController = "/api/reservations";
    // Read Branch Reservations URL
    public String readBranchReservationsUrl(Long branchId) {
        return modelServerUrl + reservationsController + "/branches/" + branchId;
    }

    // Read Car Reservations
    public String readCarReservationsUrl(Long carId) {
        return modelServerUrl + reservationsController + "/cars/" + carId;
    }

    // Read Customer Reservations
    public String readCustomerReservationsUrl(Long customerId) {
        return modelServerUrl + reservationsController + "/customers/" + customerId;
    }

    // Read Branch Info for Confirm Booking
    public String readBranchInfoUrl(Long rentalId, Long branchId) {
        return modelServerUrl + reservationsController + "/rentals/" + rentalId + "/branches/" + branchId;
    }

    // Create new Reservation URL
    public String createReservationUrl() {
        return modelServerUrl + reservationsController;
    }

    // Update Reservation
    public String updateReservationUrl(Long reservationId) {
        return modelServerUrl + reservationsController + "/" + reservationId;
    }

    // Delete Reservation
    public String deleteReservationUrl(Long reservationId) {
        return modelServerUrl + reservationsController + "/" + reservationId;
    }

}
