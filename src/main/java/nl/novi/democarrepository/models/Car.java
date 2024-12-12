package nl.novi.democarrepository.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotNull(message = "Year cannot be null")
    @Min(value = 1886, message = "Year must be after 1886")
    @AssertTrue(message = "Year must not be in the future")
    public boolean isYearValid() {
        if (year == null) {
            return true;
        }
        int currentYear = Year.now().getValue();
        return year <= currentYear;
    }
    private Integer year;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepairInvoice> repairInvoices;

    @ManyToMany
    @JoinTable(
            name = "car_accessories",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory_id")
    )
    private List<Accessory> accessories;

    // Constructors, Getters, and Setters
    public Car() {}

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

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
}
