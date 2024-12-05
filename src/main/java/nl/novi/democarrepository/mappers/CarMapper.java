package nl.novi.democarrepository.mappers;

import nl.novi.democarrepository.dtos.CarResponseDTO;
import nl.novi.democarrepository.models.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarResponseDTO toResponseDTO(Car car) {
        CarResponseDTO result = new CarResponseDTO();

        result.setId(car.getId());
        result.setModel(car.getModel());
        result.setBrand(car.getBrand());
        result.setYear(car.getYear());

        return result;
    }

    public static List<CarResponseDTO> toResponseDTOList(List<Car> cars) {
        return cars.stream().map(CarMapper::toResponseDTO).collect(Collectors.toList());
    }
}
