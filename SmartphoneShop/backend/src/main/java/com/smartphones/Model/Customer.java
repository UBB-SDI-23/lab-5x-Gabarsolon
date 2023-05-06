package com.smartphones.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The first name cannot be empty")
    private String firstName;
    @NotEmpty(message = "The last name cannot be empty")
    private String lastName;
    @Digits(integer = 10, fraction =0, message = "The phone number must be at least 10 digits long")
    @Column(unique = true)
    private String phoneNumber;
    @PastOrPresent(message = "The date of birth is invalid")
    private LocalDate dateOfBirth;
    @Email(message = "The email is invalid")
    @Column(unique = true)
    private String email;

    @Formula("(SELECT SUM(transaction.quantity) " +
            "FROM transaction " +
            "INNER JOIN customer ON transaction.customer_id = customer.id " +
            "WHERE customer.id = id)")
    private Integer totalNumberOfBoughtSmartphones;
    protected Customer() {

    }
    public Customer(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public Integer getTotalNumberOfBoughtSmartphones() {
        return totalNumberOfBoughtSmartphones;
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

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
