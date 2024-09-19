package org.rent_master.car_rental_reservation_system.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReservationCreationDTO {

    private Long carId;
    private Long customerId;
    private String dateFrom;
    private String dateTo;
    private BigDecimal amount;
    private boolean isPaid;

}
