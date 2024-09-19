package org.rent_master.car_rental_reservation_system.models;
;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rent_master.car_rental_reservation_system.auth.authmodel.User;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental extends User {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Your logotype image is required")
    private String logo;


}