package org.rent_master.car_rental_reservation_system.auth;

import jakarta.servlet.http.HttpServletRequest;

import org.rent_master.car_rental_reservation_system.DTO.CustomerCreationDTO;
import org.rent_master.car_rental_reservation_system.DTO.RentalCreationDTO;
import org.rent_master.car_rental_reservation_system.DTO.login.BusinessLoginResponse;
import org.rent_master.car_rental_reservation_system.DTO.login.CustomerLoginResponse;
import org.rent_master.car_rental_reservation_system.DTO.login.EmployeeLoginResponse;
import org.rent_master.car_rental_reservation_system.DTO.login.LoginRequest;
import org.rent_master.car_rental_reservation_system.DTO.logout.LogoutResponse;
import org.rent_master.car_rental_reservation_system.auth.authmodel.User;
import org.rent_master.car_rental_reservation_system.auth.authservice.TokenBlacklistService;
import org.rent_master.car_rental_reservation_system.auth.authservice.UserDetailsImpl;
import org.rent_master.car_rental_reservation_system.auth.authservice.UserService;
import org.rent_master.car_rental_reservation_system.auth.tokens.JwtCore;
import org.rent_master.car_rental_reservation_system.configuration.UrlConfig;
import org.rent_master.car_rental_reservation_system.models.Rental;
import org.rent_master.car_rental_reservation_system.services.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;
    private final ControllerService controllerService;
    private final TokenBlacklistService tokenBlacklistService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtCore jwtCore, TokenBlacklistService tokenBlacklistService,
                          UserService userService, UrlConfig urlConfig, RestTemplate restTemplate, ControllerService controllerService) {
        this.jwtCore = jwtCore;
        this.tokenBlacklistService = tokenBlacklistService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
        this.controllerService = controllerService;
    }


    // POST : Business Login
    @PostMapping("/rentals/sign-in")
    public ResponseEntity<?> businessLogin(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtCore.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            BusinessLoginResponse businessLoginResponse = new BusinessLoginResponse(jwt, user.getId(), ((Rental)user).getLogo());

            return ResponseEntity.ok(businessLoginResponse); // 200 OK
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // POST : Employee Login
    @PostMapping("/rentals/employees/sign-in")
    public ResponseEntity<?> employeeLogin(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtCore.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            EmployeeLoginResponse employeeLoginResponse = new EmployeeLoginResponse(jwt, user.getId());

            return ResponseEntity.ok(employeeLoginResponse); // 200 OK
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // POST : Customer Login
    @PostMapping("/customers/sign-in")
    public ResponseEntity<?> customerLogin(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtCore.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            CustomerLoginResponse customerLoginResponse = new CustomerLoginResponse(jwt, user.getId());

            return ResponseEntity.ok(customerLoginResponse); // 200 OK
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // POST : Logout
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(token);
        tokenBlacklistService.invalidateToken(token);

        LogoutResponse response = new LogoutResponse(true, "Logout successful");
        return ResponseEntity.ok(response);
    }

    // POST : Rentals Sign-up
    @PostMapping("/rentals/sign-up")
    public ResponseEntity<?> createRental(@ModelAttribute RentalCreationDTO rentalCreationDTO) {
        String url = urlConfig.createRentalUrl();
        System.out.println("Create Rental URL: " + url);

        // Create Body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body = controllerService.convertFromRentalCreationDTO(rentalCreationDTO);
        } catch (IOException e) {
            System.out.println("Show most go on!");
        }

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        controllerService.deleteTempFiles("logo");
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // POST : Create new Customer
    @PostMapping("/customers/sign-up")
    public ResponseEntity<?> createCustomer(@ModelAttribute CustomerCreationDTO customerCreationDTO) {
        String url = urlConfig.createCustomerUrl();
        System.out.println("Create Customer URL: " + url);

        HttpEntity<CustomerCreationDTO> requestEntity = new HttpEntity<>(customerCreationDTO);
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}