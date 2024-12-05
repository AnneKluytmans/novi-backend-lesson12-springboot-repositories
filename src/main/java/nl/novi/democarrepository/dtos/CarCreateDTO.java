package nl.novi.democarrepository.dtos;

import jakarta.validation.constraints.*;

import java.time.Year;

public class CarCreateDTO {
    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotNull(message = "Year cannot be null")
    @Min(value = 1886, message = "Year must be after 1886")
    private Integer year;

    @AssertTrue(message = "Year must not be in the future")
    public boolean isYearValid() {
        if (year == null) {
            return true;
        }
        int currentYear = Year.now().getValue();
        return year <= currentYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
