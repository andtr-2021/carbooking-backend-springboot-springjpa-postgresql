package com.example.backend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.BookingEntity;
import com.example.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/bookings")
    public List<BookingEntity> getAllBookings(){
        return bookingRepository.findAll();
    }

    @PostMapping("/bookings")
    public BookingEntity createBooking(@RequestBody BookingEntity booking) {
        return bookingRepository.save(booking);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingEntity> getBookingById(@PathVariable Long id) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id :" + id));
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<BookingEntity> updateBooking(@PathVariable Long id, @RequestBody BookingEntity bookingDetails){
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id :" + id));

        booking.setBookingDate(bookingDetails.getBookingDate());
        booking.setUser(bookingDetails.getUser());
        booking.setBookingPickUpLocation(bookingDetails.getBookingPickUpLocation());
        booking.setBookingDropOffLocation(bookingDetails.getBookingDropOffLocation());
        booking.setBookingPickUpTime(bookingDetails.getBookingPickUpTime());
        booking.setBookingDropOffTime(bookingDetails.getBookingDropOffTime());
        booking.setBookingNumberOfPassengers(bookingDetails.getBookingNumberOfPassengers());
        booking.setDriver(bookingDetails.getDriver());
        booking.setVehicle(bookingDetails.getVehicle());
        booking.setPayment(bookingDetails.getPayment());

        BookingEntity updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }

    // delete employee rest api
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBooking(@PathVariable Long id){
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id :" + id));

        bookingRepository.delete(booking);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
