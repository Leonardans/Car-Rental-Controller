package org.rent_master.car_rental_reservation_system.DTO.logout;

import lombok.Data;

@Data
public class LogoutResponse {

    private boolean success;
    private String message;

    public LogoutResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
