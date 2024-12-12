package nl.novi.democarrepository.dtos;

import java.time.LocalDate;

public class CarRegistrationCreateDTO {
    private String plateNumber;
    private LocalDate registrationDate;

    // Getters en Setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}