package nl.novi.democarrepository.mappers;

import nl.novi.democarrepository.dtos.CarRegistrationCreateDTO;
import nl.novi.democarrepository.dtos.CarRegistrationResponseDTO;
import nl.novi.democarrepository.dtos.CarRegistrationUpdateDTO;
import nl.novi.democarrepository.models.CarRegistration;

import java.util.List;
import java.util.stream.Collectors;

public class CarRegistrationMapper {

    // Zet een CarRegistration entiteit om naar een CarRegistrationResponseDTO
    public static CarRegistrationResponseDTO toResponseDTO(CarRegistration carRegistration) {
        CarRegistrationResponseDTO dto = new CarRegistrationResponseDTO();
        dto.setId(carRegistration.getId());
        dto.setPlateNumber(carRegistration.getPlateNumber());
        dto.setRegistrationDate(carRegistration.getRegistrationDate());
        return dto;
    }

    // Zet een CarRegistrationCreateDTO om naar een CarRegistration entiteit
    public static CarRegistration toEntity(CarRegistrationCreateDTO carRegistrationCreateDTO) {
        CarRegistration carRegistration = new CarRegistration();
        carRegistration.setPlateNumber(carRegistrationCreateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationCreateDTO.getRegistrationDate());
        return carRegistration;
    }

    // Zet een CarRegistrationUpdateDTO om naar een CarRegistration entiteit
    public static CarRegistration toEntity(CarRegistrationUpdateDTO carRegistrationUpdateDTO) {
        CarRegistration carRegistration = new CarRegistration();
        carRegistration.setPlateNumber(carRegistrationUpdateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationUpdateDTO.getRegistrationDate());
        return carRegistration;
    }

    // Zet een lijst van CarRegistration entiteiten om naar een lijst van CarRegistrationResponseDTO's
    public static List<CarRegistrationResponseDTO> toResponseDTOList(List<CarRegistration> carRegistrations) {
        return carRegistrations.stream()
                .map(CarRegistrationMapper::toResponseDTO) // Zet elke CarRegistration om naar een CarRegistrationResponseDTO
                .collect(Collectors.toList());
    }
}