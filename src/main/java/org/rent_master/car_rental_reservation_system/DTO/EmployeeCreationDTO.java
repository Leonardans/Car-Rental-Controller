package org.rent_master.car_rental_reservation_system.DTO;

import lombok.Data;

@Data
public class EmployeeCreationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String position;

}
