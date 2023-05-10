package com.example.backend.model;

import com.example.backend.repository.DriverRepository;
import jakarta.persistence.*;

@Entity
@Table(name="TBL_DRIVERS")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DriverEntity() {

    }

    public DriverEntity(String driverName, String driverPhoneNumber, String driverLicenseNumber, String driverYearOfExperience, String driverAvatar, String driverPassword) {
        this.driverName = driverName;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverYearOfExperience = driverYearOfExperience;
        this.driverAvatar = driverAvatar;
        this.driverPassword = driverPassword;
    }

    @Column(name="driver_name")
    private String driverName;

    @Column(name="driver_phone_number")
    private String driverPhoneNumber;

    @Column(name="driver_license_number")
    private String driverLicenseNumber;

    @Column(name="driver_year_of_experience")
    private String driverYearOfExperience;

    @Column(name="driver_avatar")
    private String driverAvatar;

    @Column(name="driver_password")
    private String driverPassword;

    public Long getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public String getDriverYearOfExperience() {
        return driverYearOfExperience;
    }

    public String getDriverAvatar() {
        return driverAvatar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public void setDriverYearOfExperience(String driverYearOfExperience) {
        this.driverYearOfExperience = driverYearOfExperience;
    }

    public void setDriverAvatar(String driverAvatar) {
        this.driverAvatar = driverAvatar;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

}
