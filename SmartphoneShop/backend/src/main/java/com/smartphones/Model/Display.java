package com.smartphones.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Entity
public class Display {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The first name cannot be empty")
    private String type;
    @Positive(message = "The size must be positive")
    private Double size;
    @Positive(message = "The resolutionWidth must be positive")
    private Integer resolutionWidth;
    @Positive(message = "The resolutionHeight must be positive")
    private Integer resolutionHeight;
    @Formula("(SELECT COUNT(*) FROM smartphone WHERE smartphone.display_id = id)")
    private Integer smartphoneCount;

    private String protection;
//    @OneToMany(mappedBy = "display", orphanRemoval = true, fetch = FetchType.LAZY)
//    private Set<Smartphone> smartphones;
    protected Display(){

    }
    public Display(String type, Double size, Integer resolutionWidth, Integer resolutionHeight, String protection) {
        this.type = type;
        this.size = size;
        this.resolutionWidth = resolutionWidth;
        this.resolutionHeight = resolutionHeight;
        this.protection = protection;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getSize() {
        return size;
    }

    public Integer getResolutionWidth() {
        return resolutionWidth;
    }

    public Integer getResolutionHeight() {
        return resolutionHeight;
    }

    public String getProtection() {
        return protection;
    }

//    public Set<Smartphone> getSmartphones() {
//        return smartphones;
//    }


    public Integer getSmartphoneCount() {
        return smartphoneCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setResolutionWidth(Integer resolutionWidth) {
        this.resolutionWidth = resolutionWidth;
    }

    public void setResolutionHeight(Integer resolutionHeight) {
        this.resolutionHeight = resolutionHeight;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

//    public void setSmartphones(Set<Smartphone> smartphones) {
//        this.smartphones = smartphones;
//    }

    @Override
    public String toString() {
        return "Display{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", resolutionWidth=" + resolutionWidth +
                ", resolutionHeight=" + resolutionHeight +
                ", protection='" + protection + '\'' +
                '}';
    }
}
