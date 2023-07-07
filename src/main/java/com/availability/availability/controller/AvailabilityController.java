package com.availability.availability.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.availability.availability.model.Availability;
import com.availability.availability.repository.AvailabilityRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    // Adding an availability
    @PostMapping("/add")
    public ResponseEntity<Availability> addAnAvailability(@RequestBody Availability availability) {
        try {
            Availability savedAvailability = availabilityRepository.save(availability);
            return ResponseEntity.ok(savedAvailability);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Getting all availabilities
    @GetMapping("/all")
    public ResponseEntity<List<Availability>> getAllAvailabilities() {
        try {
            List<Availability> availabilities = availabilityRepository.findAll();
            return ResponseEntity.ok(availabilities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Getting an availability
    @GetMapping("/{id}")
    public ResponseEntity<Availability> getAnAvailability(@PathVariable Long id) {
        try {
            Availability availability = availabilityRepository.findById(id).orElse(null);
            if (availability != null) {
                return ResponseEntity.ok(availability);
            } else {
                return ResponseEntity.ok(availability);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
  @GetMapping("/counselor/{counselorId}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByCounselorId(@PathVariable Long counselorId) {
        try {
            List<Availability> availabilities = availabilityRepository.findBycounselorId(counselorId);
            if (!availabilities.isEmpty()) {
                return ResponseEntity.ok(availabilities);
            } else {
                return ResponseEntity.ok(availabilities);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // Delete All Availabilities
    @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAllAvailabilities() {
        try {
            availabilityRepository.deleteAll();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete an availability
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnAvailability(@PathVariable Long id) {
        try {
            Availability availability = availabilityRepository.findById(id).orElse(null);
            if(Objects.isNull(availability)){
                return ResponseEntity.ok().build();
            }
            else{
                availabilityRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update an availability
    @PutMapping("/update")
    public ResponseEntity<Availability> updateAnAvailability(@RequestBody Availability availability) {
        try {
            Availability updatedAvailability = availabilityRepository.save(availability);
            return ResponseEntity.ok(updatedAvailability);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

