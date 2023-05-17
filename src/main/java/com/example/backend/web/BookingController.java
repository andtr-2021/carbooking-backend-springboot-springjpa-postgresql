package com.example.backend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.BookingEntity;
import com.example.backend.model.PaymentEntity;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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
        booking.setStatus(bookingDetails.getStatus());

        BookingEntity updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBooking(@PathVariable Long id){
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id :" + id));

        bookingRepository.delete(booking);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/bookings/status/{id}")
    public ResponseEntity<BookingEntity> updateBookingStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking does not exist with id: " + id));

        String status = statusMap.get("status");
        booking.setStatus(status);

        if (status.equals("finish")) {
            PaymentEntity payment = booking.getPayment();
            payment.setPaymentStatus("paid");
            paymentRepository.save(payment);
        }

        BookingEntity updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }

    @PatchMapping("/bookings/{id}")
    public ResponseEntity<BookingEntity> updateBookingDetails(@PathVariable Long id, @RequestBody BookingEntity updatedBooking) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking does not exist with id: " + id));

        if (updatedBooking.getBookingDate() != null) {
            booking.setBookingDate(updatedBooking.getBookingDate());
        }

        if (updatedBooking.getBookingPickUpTime() != null) {
            booking.setBookingPickUpTime(updatedBooking.getBookingPickUpTime());
        }

        if (updatedBooking.getBookingPickUpLocation() != null) {
            booking.setBookingPickUpLocation(updatedBooking.getBookingPickUpLocation());
        }

        if (updatedBooking.getBookingDropOffLocation() != null) {
            booking.setBookingDropOffLocation(updatedBooking.getBookingDropOffLocation());
        }

        if (updatedBooking.getBookingNumberOfPassengers() != null) {
            booking.setBookingNumberOfPassengers(updatedBooking.getBookingNumberOfPassengers());
        }

        BookingEntity updatedBookingEntity = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBookingEntity);
    }

}
