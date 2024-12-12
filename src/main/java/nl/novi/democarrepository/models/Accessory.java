package nl.novi.democarrepository.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "accessories")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToMany(mappedBy = "accessories")
    private List<Car> cars;

    // Constructor
    public Accessory() {}

    public Accessory(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}