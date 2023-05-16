package com.example.backend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.UserEntity;
import com.example.backend.repository.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    // get employee by id rest api
    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        user.setUserName(userDetails.getUserName());
        user.setUserEmail(userDetails.getUserEmail());
        user.setUserPassword(userDetails.getUserPassword());
        user.setUserPhoneNumber(userDetails.getUserPhoneNumber());


        UserEntity updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // delete employee rest api
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
