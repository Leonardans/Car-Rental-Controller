package org.rent_master.car_rental_reservation_system.DTO.login;

import lombok.Data;

@Data
public class BusinessLoginResponse {

    private String token;
    private Long businessId;
    private String logoFileName;


    public BusinessLoginResponse(String token, Long businessId, String logoFileName) {
        this.token = token;
        this.businessId = businessId;
        this.logoFileName = logoFileName;
    }

}
