package com.example.backend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.VehicleEntity;
import com.example.backend.repository.VehicleRepository;
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
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/vehicles")
    public List<VehicleEntity> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    @PostMapping("/vehicles")
    public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable Long id) {
        VehicleEntity vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not exist with id :" + id));
        return ResponseEntity.ok(vehicle);
    }


    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehicleEntity> updateVehicle(@PathVariable Long id, @RequestBody VehicleEntity vehicleDetails){
        VehicleEntity vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not exist with id :" + id));

        vehicle.setName(vehicleDetails.getName());
        vehicle.setBrand(vehicleDetails.getBrand());
        vehicle.setPlate(vehicleDetails.getPlate());
        vehicle.setColor(vehicleDetails.getColor());
        vehicle.setSeatNumber(vehicleDetails.getSeatNumber());

        VehicleEntity updatedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

    // delete employee rest api
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteVehicle(@PathVariable Long id){
        VehicleEntity vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not exist with id :" + id));

        vehicleRepository.delete(vehicle);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
