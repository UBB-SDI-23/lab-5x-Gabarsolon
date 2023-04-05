package com.smartphones.Model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Smartphone{
    private @Id @GeneratedValue(strategy= GenerationType.AUTO) Long id;
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
    protected Smartphone(){

    };
    public Smartphone(String brand, String model, BigDecimal price, Integer storageCapacity, LocalDate launchDate,  Display display){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.storageCapacity = storageCapacity;
        this.launchDate = launchDate;
        this.display = display;

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
                '}';
    }
}
