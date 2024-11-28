package nl.novi.democarrepository.controllers;

import nl.novi.democarrepository.models.Car;
import nl.novi.democarrepository.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false) String brand) {
        List<Car> cars = (brand != null) ? carRepository.findByBrand(brand) : carRepository.findAll();
        return ResponseEntity.ok(cars);
    }
}
