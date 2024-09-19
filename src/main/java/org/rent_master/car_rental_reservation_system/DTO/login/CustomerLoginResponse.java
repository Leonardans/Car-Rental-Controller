package org.rent_master.car_rental_reservation_system.DTO.login;

import lombok.Data;

@Data
public class CustomerLoginResponse {

    private String token;
    private Long customerId;

    public CustomerLoginResponse(String token, Long customerId) {
        this.token = token;
        this.customerId = customerId;
    }

}
