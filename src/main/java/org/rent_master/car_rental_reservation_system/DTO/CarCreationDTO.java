package org.rent_master.car_rental_reservation_system.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class CarCreationDTO {

    private BigDecimal amount;
    private Integer mileage;
    private Integer year;
    private String carStateNumberPlate;
    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private MultipartFile[] pictures;

}
