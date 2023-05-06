package com.smartphones.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "You must specify a customer id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @NotNull(message = "You must specify a smartphone id")
    private Smartphone smartphone;
    @Positive(message = "The quantity must be positive")
    private Integer quantity;
    @PastOrPresent(message = "Invalid date")
    private LocalDateTime dateTime;


    @Formula("(SELECT transaction.quantity*smartphone.price\n" +
            "FROM transaction INNER JOIN smartphone ON transaction.smartphone_id = smartphone.id\n" +
            "INNER JOIN customer ON transaction.customer_id = customer.id\n" +
            "WHERE transaction.id = id)")
    private Double checkout;
    protected Transaction(){

    }

    public Transaction(Customer customer, Smartphone smartphone, Integer quantity, LocalDateTime dateTime) {
        this.customer = customer;
        this.smartphone = smartphone;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Smartphone getSmartphone() {
        return smartphone;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Double getCheckout() {
        return checkout;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setSmartphone(Smartphone smartphone) {
        this.smartphone = smartphone;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
