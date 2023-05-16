package com.example.backend.repository;

import com.example.backend.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
