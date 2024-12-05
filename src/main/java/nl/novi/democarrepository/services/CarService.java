package nl.novi.democarrepository.services;

import jakarta.validation.Valid;
import nl.novi.democarrepository.models.Car;
import nl.novi.democarrepository.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByBrandAndModel(String brand, String model) {
        if (brand != null && model != null) {
            return carRepository.findByBrandAndModel(brand, model);
        } else if (brand != null) {
            return carRepository.findByBrand(brand);
        } else if (model != null) {
            return carRepository.findByModel(model);
        } else {
            return carRepository.findAll();
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public boolean carExists(Long id) {
        return carRepository.existsById(id);
    }
}
