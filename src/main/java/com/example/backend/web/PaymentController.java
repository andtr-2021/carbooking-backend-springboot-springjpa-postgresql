package com.example.backend.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.PaymentEntity;
import com.example.backend.repository.PaymentRepository;
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
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments")
    public List<PaymentEntity> getAllPayments() { return paymentRepository.findAll(); }

    @PostMapping("/payments")
    public PaymentEntity createPayment(@RequestBody PaymentEntity payment) {
        return paymentRepository.save(payment);
    }

    // get employee by id rest api
    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentEntity> getPaymentById(@PathVariable Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not exist with id :" + id));
        return ResponseEntity.ok(payment);
    }

    // update employee rest api

    @PutMapping("/payments/{id}")
    public ResponseEntity<PaymentEntity> updatePayment(@PathVariable Long id, @RequestBody PaymentEntity paymentDetails){
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not exist with id :" + id));

        payment.setPaymentType(paymentDetails.getPaymentType());
        payment.setPaymentAmount(paymentDetails.getPaymentAmount());
        payment.setPaymentStatus(paymentDetails.getPaymentStatus());
        payment.setPaymentDate(paymentDetails.getPaymentDate());


        PaymentEntity updatedPayment = paymentRepository.save(payment);
        return ResponseEntity.ok(updatedPayment);
    }

    // delete employee rest api
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePayment(@PathVariable Long id){
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not exist with id :" + id));

        paymentRepository.delete(payment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
