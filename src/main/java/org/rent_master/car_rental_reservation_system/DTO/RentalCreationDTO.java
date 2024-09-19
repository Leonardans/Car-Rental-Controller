package org.rent_master.car_rental_reservation_system.DTO;

import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalCreationDTO {

    private String name;
    private String owner;
    private String username;
    private String password;
    private String email;
    private String address1;
    @Nullable
    private String address2;
    private String city;
    private String country;
    private MultipartFile logo;

}
