package org.rent_master.car_rental_reservation_system.services;

import org.rent_master.car_rental_reservation_system.DTO.CarCreationDTO;
import org.rent_master.car_rental_reservation_system.DTO.RentalCreationDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ControllerService {

    private final Map<String, List<File>> tempFiles;


    public ControllerService() {
        this.tempFiles = new HashMap<>();
    }


    // Prepare Rental Creation Data
    public MultiValueMap<String, Object> convertFromRentalCreationDTO(RentalCreationDTO rentalCreationDTO) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", rentalCreationDTO.getName());
        body.add("owner", rentalCreationDTO.getOwner());
        body.add("username", rentalCreationDTO.getUsername());
        body.add("password", rentalCreationDTO.getPassword());
        body.add("email", rentalCreationDTO.getEmail());
        body.add("address1", rentalCreationDTO.getAddress1());
        body.add("address2", rentalCreationDTO.getAddress2());
        body.add("city", rentalCreationDTO.getCity());
        body.add("country", rentalCreationDTO.getCountry());

        // Check if the logo is provided
        if (rentalCreationDTO.getLogo() != null && !rentalCreationDTO.getLogo().isEmpty()) {
            MultipartFile logoFile = rentalCreationDTO.getLogo();
            File tempPictureFile = File.createTempFile("logo", logoFile.getOriginalFilename());
            logoFile.transferTo(tempPictureFile);
            tempFiles.computeIfAbsent("logo", k -> new ArrayList<>()).add(tempPictureFile);

            FileSystemResource fileSystemResource = new FileSystemResource(tempPictureFile);
            body.add("logo", fileSystemResource);
        }

        return body;
    }

    // Prepare Car Creation Data
    public MultiValueMap<String, Object> convertFromCarCreationDTO(CarCreationDTO carCreationDTO) throws IOException {

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("amount", carCreationDTO.getAmount());
        body.add("mileage", carCreationDTO.getMileage());
        body.add("year", carCreationDTO.getYear());
        body.add("carStateNumberPlate", carCreationDTO.getCarStateNumberPlate());
        body.add("brand", carCreationDTO.getBrand());
        body.add("model", carCreationDTO.getModel());
        body.add("bodyType", carCreationDTO.getBodyType());
        body.add("color", carCreationDTO.getColor());


        if (carCreationDTO.getPictures() != null) {
            for (MultipartFile picture : carCreationDTO.getPictures()) {
                if (picture != null && !picture.isEmpty()) {
                    File tempPictureFile = File.createTempFile("car_pictures", picture.getOriginalFilename());
                    picture.transferTo(tempPictureFile);
                    tempFiles.computeIfAbsent("car_pictures", k -> new ArrayList<>()).add(tempPictureFile);

                    FileSystemResource fileSystemResource = new FileSystemResource(tempPictureFile);
                    body.add("pictures", fileSystemResource);
                }
            }
        }

        return body;
    }

    // Delete Temp Files
    public void deleteTempFiles(String name) {
        if (tempFiles.containsKey(name)) {
            List<File> files = tempFiles.get(name);
            for (File file : files) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Temporary file '" + file.getName() + "' successfully deleted.");
                } else {
                    System.out.println("Failed to delete temporary file '" + file.getName() + "'.");
                }
            }
            tempFiles.remove(name);
        } else {
            System.out.println("No temporary files found with the name '" + name + "'.");
        }
    }

}
