package org.rent_master.car_rental_reservation_system.DTO.login;

import lombok.Data;

@Data
public class EmployeeLoginResponse {

    private String token;
    private Long employeeId;

    public EmployeeLoginResponse(String token, Long employeeId) {
        this.token = token;
        this.employeeId = employeeId;
    }

}
