package com.example.backend.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.DriverEntity;
import com.example.backend.repository.DriverRepository;
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
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    // get all employees
    @GetMapping("/drivers")
    public List<DriverEntity> getAllDrivers(){
        return driverRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/drivers")
    public DriverEntity createDriver(@RequestBody DriverEntity driver) {
        return driverRepository.save(driver);
    }

    // get employee by id rest api
    @GetMapping("/drivers/{id}")
    public ResponseEntity<DriverEntity> getDriverById(@PathVariable Long id) {
        DriverEntity driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id :" + id));
        return ResponseEntity.ok(driver);
    }

    // update employee rest api

    @PutMapping("/drivers/{id}")
    public ResponseEntity<DriverEntity> updateDriver(@PathVariable Long id, @RequestBody DriverEntity driverDetails){
        DriverEntity driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id :" + id));

        driver.setDriverName(driverDetails.getDriverName());
        driver.setDriverPhoneNumber(driverDetails.getDriverPhoneNumber());
        driver.setDriverLicenseNumber(driverDetails.getDriverLicenseNumber());
        driver.setDriverYearOfExperience(driverDetails.getDriverYearOfExperience());
        driver.setDriverAvatar(driverDetails.getDriverAvatar());
        driver.setDriverPassword(driverDetails.getDriverPassword());

        DriverEntity updatedDriver = driverRepository.save(driver);
        return ResponseEntity.ok(updatedDriver);
    }

    // delete employee rest api
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDriver(@PathVariable Long id){
        DriverEntity driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id :" + id));

        driverRepository.delete(driver);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
