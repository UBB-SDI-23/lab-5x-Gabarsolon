package com.lab1gabarsolon.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TransactionDTO {
    private String firstName;
    private String lastName;
    private LocalDate dob;

    private List<SmartphoneDTO> smartphoneSet;

    public TransactionDTO(String firstName, String lastName, LocalDate dob, List<SmartphoneDTO> smartphoneSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.smartphoneSet = smartphoneSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public List<SmartphoneDTO> getSmartphoneSet() {
        return smartphoneSet;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setSmartphoneSet(List<SmartphoneDTO> smartphoneSet) {
        this.smartphoneSet = smartphoneSet;
    }
}
