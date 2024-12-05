package nl.novi.democarrepository.dtos;

import java.time.LocalDate;

public class CarResponseDTO {
    //hier geef ja alleen de velden op die je wil teruggeven, dus bijv. geen password
    private Long id;
    private String brand;
    private String model;
    private int year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getAge() {
        return LocalDate.now().getYear() - this.year;
    }
}
