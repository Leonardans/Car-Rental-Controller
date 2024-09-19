package org.rent_master.car_rental_reservation_system.DTO;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class BranchCreationDTO {

    private String address1;
    @Nullable
    private String address2;
    private String city;
    private String country;

}


