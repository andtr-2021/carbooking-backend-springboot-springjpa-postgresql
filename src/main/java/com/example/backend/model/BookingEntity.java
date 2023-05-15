package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="TBL_BOOKINGS")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BookingEntity() {

    }

    public BookingEntity(String bookingDate, UserEntity User, String bookingPickUpLocation, String bookingDropOffLocation, String bookingPickUpTime, String bookingDropOffTime, String bookingNumberOfPassengers, VehicleEntity Vehicle, DriverEntity Driver, PaymentEntity Payment) {
        this.bookingDate = bookingDate;
        this.User = User;
        this.bookingPickUpLocation = bookingPickUpLocation;
        this.bookingDropOffLocation = bookingDropOffLocation;
        this.bookingPickUpTime = bookingPickUpTime;
        this.bookingDropOffTime = bookingDropOffTime;
        this.bookingNumberOfPassengers = bookingNumberOfPassengers;
        this.Vehicle = Vehicle;
        this.Driver = Driver;
        this.Payment = Payment;
    }

    @Column(name="booking_date")
    private String bookingDate;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private UserEntity User;

    @Column(name="booking_pick_up_location")
    private String bookingPickUpLocation;

    @Column(name="booking_drop_off_location")
    private String bookingDropOffLocation;

    @Column(name="booking_pick_up_time")
    private String bookingPickUpTime;

    @Column(name="booking_drop_off_time")
    private String bookingDropOffTime;

    @Column(name="booking_number_of_passengers")
    private String bookingNumberOfPassengers;

    @ManyToOne
    private VehicleEntity Vehicle;

    @ManyToOne
    private DriverEntity Driver;

    @OneToOne
    private PaymentEntity Payment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public UserEntity getUser() {
        return User;
    }

    public void setUser(UserEntity user) {
        User = user;
    }

    public String getBookingPickUpLocation() {
        return bookingPickUpLocation;
    }

    public void setBookingPickUpLocation(String bookingPickUpLocation) {
        this.bookingPickUpLocation = bookingPickUpLocation;
    }

    public String getBookingDropOffLocation() {
        return bookingDropOffLocation;
    }

    public void setBookingDropOffLocation(String bookingDropOffLocation) {
        this.bookingDropOffLocation = bookingDropOffLocation;
    }

    public String getBookingPickUpTime() {
        return bookingPickUpTime;
    }

    public void setBookingPickUpTime(String bookingPickUpTime) {
        this.bookingPickUpTime = bookingPickUpTime;
    }

    public String getBookingDropOffTime() {
        return bookingDropOffTime;
    }

    public void setBookingDropOffTime(String bookingDropOffTime) {
        this.bookingDropOffTime = bookingDropOffTime;
    }

    public String getBookingNumberOfPassengers() {
        return bookingNumberOfPassengers;
    }

    public void setBookingNumberOfPassengers(String bookingNumberOfPassengers) {
        this.bookingNumberOfPassengers = bookingNumberOfPassengers;
    }

    public VehicleEntity getVehicle() {
        return Vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        Vehicle = vehicle;
    }

    public DriverEntity getDriver() {
        return Driver;
    }

    public void setDriver(DriverEntity driver) {
        Driver = driver;
    }

    public PaymentEntity getPayment() {
        return Payment;
    }

    public void setPayment(PaymentEntity payment) {
        Payment = payment;
    }
}
