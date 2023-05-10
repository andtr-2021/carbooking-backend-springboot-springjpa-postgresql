package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="TBL_VEHICLES")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public VehicleEntity() {

    }

    public VehicleEntity(String name, String brand, String plate, String color, String seatNumber) {
        this.name = name;
        this.brand = brand;
        this.plate = plate;
        this.color = color;
        this.seatNumber = seatNumber;
    }

    @Column(name="name")
    private String name;

    @Column(name="brand")
    private String brand;

    @Column(name="plate")
    private String plate;

    @Column(name="color")
    private String color;

    @Column(name="seat_number")
    private String seatNumber;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getPlate() {
        return plate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
