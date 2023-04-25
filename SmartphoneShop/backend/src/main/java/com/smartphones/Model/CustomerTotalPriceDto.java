package com.smartphones.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerTotalPriceDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private BigDecimal totalPrice;

    public CustomerTotalPriceDto(Long id, String firstName, String lastName, String phoneNumber, BigDecimal totalPrice) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CustomerTotalPriceDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
