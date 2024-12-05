package nl.novi.democarrepository.controllers;

import jakarta.validation.Valid;
import nl.novi.democarrepository.dtos.CarCreateDTO;
import nl.novi.democarrepository.dtos.CarResponseDTO;
import nl.novi.democarrepository.mappers.CarMapper;
import nl.novi.democarrepository.models.Car;
import nl.novi.democarrepository.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CarCreateDTO carCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Car car = CarMapper.toEntity(carCreateDTO);
        Car savedCar = carService.saveCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(CarMapper.toResponseDTO(savedCar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> carOptional = carService.getCarById(id);
        if (carOptional.isPresent()) {
            Car updatedCar = carOptional.get();
            updatedCar.setBrand(carDetails.getBrand());
            updatedCar.setModel(carDetails.getModel());
            carService.saveCar(updatedCar);
            return ResponseEntity.ok(CarMapper.toResponseDTO(updatedCar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carService.carExists(id)) {
            carService.deleteCar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model) {

        List<Car> cars = carService.getCarsByBrandAndModel(brand, model);
        return ResponseEntity.ok(CarMapper.toResponseDTOList(cars));
    }

}
