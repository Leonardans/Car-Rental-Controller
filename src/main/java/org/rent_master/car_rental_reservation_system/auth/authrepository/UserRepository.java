package org.rent_master.car_rental_reservation_system.auth.authrepository;

import org.rent_master.car_rental_reservation_system.auth.authmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // READ : Check User Existing by Username
    boolean existsByUsername(String username);

    // READ : Check User Existing by Email
    boolean existsByEmail(String email);

    // READ : Find User by Username
    Optional<User> findByUsername(String username);

}
