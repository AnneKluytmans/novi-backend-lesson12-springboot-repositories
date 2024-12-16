package nl.novi.democarrepository.controllers;

import nl.novi.democarrepository.dtos.CarRegistrationCreateDTO;
import nl.novi.democarrepository.dtos.CarRegistrationResponseDTO;
import nl.novi.democarrepository.dtos.CarRegistrationUpdateDTO;
import nl.novi.democarrepository.services.CarRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cars/{carId}/carregistrations")
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;

    public CarRegistrationController(CarRegistrationService carRegistrationService) {
        this.carRegistrationService = carRegistrationService;
    }

    @PostMapping
    public ResponseEntity<CarRegistrationResponseDTO> createCarRegistration(
            @PathVariable Long carId,
            @RequestBody CarRegistrationCreateDTO carRegistrationCreateDTO) {

        CarRegistrationResponseDTO responseDTO = carRegistrationService.createCarRegistration(carId, carRegistrationCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{registrationId}")
    public ResponseEntity<CarRegistrationResponseDTO> getCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId) {

        CarRegistrationResponseDTO responseDTO = carRegistrationService.getCarRegistration(carId, registrationId);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{registrationId}")
    public ResponseEntity<CarRegistrationResponseDTO> updateCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId,
            @RequestBody CarRegistrationUpdateDTO carRegistrationUpdateDTO) {

        CarRegistrationResponseDTO responseDTO = carRegistrationService.updateCarRegistration(carId, registrationId, carRegistrationUpdateDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId) {

        boolean isDeleted = carRegistrationService.deleteCarRegistration(carId, registrationId);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
