package nl.novi.democarrepository.controllers;

import jakarta.validation.Valid;
import nl.novi.democarrepository.models.Car;
import nl.novi.democarrepository.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Car savedCar = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false) String brand, @RequestParam(required = false) String model) {
        List<Car> cars;

        if (brand != null && model != null) {
            cars = carRepository.findByBrandAndModel(brand, model);
        } else if (brand != null) {
            cars = carRepository.findByBrand(brand);
        } else if (model != null) {
            cars = carRepository.findByModel(model);
        } else {
            cars = carRepository.findAll();
        }

        return ResponseEntity.ok(cars);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        Optional<Car> savedCar = carRepository.findById(id);

        if (savedCar.isPresent()) {
            Car updatedCar = savedCar.get();
            updatedCar.setBrand(car.getBrand());
            updatedCar.setModel(car.getModel());
            carRepository.save(updatedCar);
            return ResponseEntity.ok(updatedCar);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
