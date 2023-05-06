package com.smartphones.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Smartphone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The brand cannot be empty")
    private String brand;
    @NotEmpty(message = "The model cannot be empty")
    private String model;
    @PositiveOrZero(message = "The price must be at least 0")
    private BigDecimal price;
    @Positive(message = "The storage capacity must be positive")
    private Integer storageCapacity;
    @PastOrPresent(message = "Invalid date")
    private LocalDate launchDate;


    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonProperty("displayId")
    @JoinColumn(name = "display_id")
//    @NotNull(message = "You must specify a display id")
    private Display display;
    private String description;
    @Formula("(SELECT sum(transaction.quantity)\n" +
            "FROM transaction INNER JOIN smartphone ON transaction.smartphone_id = smartphone.id\n" +
            "WHERE smartphone.id = id)")
    private Integer totalNumberOfBoughtQuantity;

    protected Smartphone(){

    };
    public Smartphone(String brand, String model, BigDecimal price, Integer storageCapacity, LocalDate launchDate,  Display display, String description){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.storageCapacity = storageCapacity;
        this.launchDate = launchDate;
        this.display = display;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStorageCapacity() {
        return storageCapacity;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public Display getDisplay() {
        return display;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTotalNumberOfBoughtQuantity() {
        return totalNumberOfBoughtQuantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStorageCapacity(Integer storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", storageCapacity=" + storageCapacity +
                ", launchDate=" + launchDate +
                ", display=" + display +
                ", description='" + description + '\'' +
                '}';
    }
}
