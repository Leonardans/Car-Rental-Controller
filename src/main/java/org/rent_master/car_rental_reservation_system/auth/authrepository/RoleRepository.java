package org.rent_master.car_rental_reservation_system.auth.authrepository;

import org.rent_master.car_rental_reservation_system.auth.authmodel.Role;
import org.rent_master.car_rental_reservation_system.auth.authmodel.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   // READ : Find by Name;
   Optional<Role> findByName(Roles name);

}