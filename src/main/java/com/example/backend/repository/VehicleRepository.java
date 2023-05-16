package com.example.backend.repository;

import com.example.backend.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

}
