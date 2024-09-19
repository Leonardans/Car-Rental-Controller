package org.rent_master.car_rental_reservation_system.DTO;

import jakarta.annotation.Nullable;
import lombok.Data;


@Data
public class CustomerCreationDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String address1;
    @Nullable
    private String address2;
    private String city;
    private String country;
    private String licence;

}
