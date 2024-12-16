package nl.novi.democarrepository.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.democarrepository.dtos.CarRegistrationCreateDTO;
import nl.novi.democarrepository.dtos.CarRegistrationResponseDTO;
import nl.novi.democarrepository.dtos.CarRegistrationUpdateDTO;
import nl.novi.democarrepository.mappers.CarRegistrationMapper;
import nl.novi.democarrepository.models.Car;
import nl.novi.democarrepository.models.CarRegistration;
import nl.novi.democarrepository.repositories.CarRegistrationRepository;
import nl.novi.democarrepository.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarRegistrationService {

    private final CarRegistrationRepository carRegistrationRepository;
    private final CarRepository carRepository;

    public CarRegistrationService(CarRegistrationRepository carRegistrationRepository, CarRepository carRepository) {
        this.carRegistrationRepository = carRegistrationRepository;
        this.carRepository = carRepository;
    }

    // Methode om een nieuwe CarRegistration aan te maken
    public CarRegistrationResponseDTO createCarRegistration(Long carId, CarRegistrationCreateDTO carRegistrationCreateDTO) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + carId));

        // Zet DTO om naar entiteit en koppel de Car
        CarRegistration carRegistration = CarRegistrationMapper.toEntity(carRegistrationCreateDTO);
        carRegistration.setCar(car);

        CarRegistration savedCarRegistration = carRegistrationRepository.save(carRegistration);
        return CarRegistrationMapper.toResponseDTO(savedCarRegistration);
    }

    // Methode om een bestaande CarRegistration bij te werken
    public CarRegistrationResponseDTO updateCarRegistration(Long carId, Long registrationId, CarRegistrationUpdateDTO carRegistrationUpdateDTO) {
        CarRegistration carRegistration = carRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("CarRegistration not found with id " + registrationId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + carId));

        // Update de registratie en koppel de juiste Car
        carRegistration.setPlateNumber(carRegistrationUpdateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationUpdateDTO.getRegistrationDate());
        carRegistration.setCar(car);

        CarRegistration updatedCarRegistration = carRegistrationRepository.save(carRegistration);
        return CarRegistrationMapper.toResponseDTO(updatedCarRegistration);
    }

    // Methode om een CarRegistration op te halen
    public CarRegistrationResponseDTO getCarRegistration(Long carId, Long registrationId) {
        CarRegistration carRegistration = carRegistrationRepository.findByIdAndCarId( registrationId, carId)
                .orElseThrow(() -> new EntityNotFoundException("CarRegistration not found with id " + registrationId));

        return CarRegistrationMapper.toResponseDTO(carRegistration);
    }

    // Methode om een CarRegistration te verwijderen
    public boolean deleteCarRegistration(Long carId, Long registrationId) {
        if (carRegistrationRepository.existsById(registrationId)) {
            carRegistrationRepository.deleteById(registrationId);
            return true;
        }
        return false;
    }
}