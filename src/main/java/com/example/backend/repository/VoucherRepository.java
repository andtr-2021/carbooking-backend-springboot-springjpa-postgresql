package com.example.backend.repository;

import com.example.backend.model.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

}
