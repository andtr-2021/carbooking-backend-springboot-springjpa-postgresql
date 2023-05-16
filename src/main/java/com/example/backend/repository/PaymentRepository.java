package com.example.backend.repository;

import com.example.backend.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
