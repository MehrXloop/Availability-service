package com.availability.availability.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


@CrossOrigin()
@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    
    @Autowired
    private AvailabilityRepository availabilityRepository;


    //Adding an availability
    @PostMapping("/add")
    public void addAnAvailability(@RequestBody Availability availability){
        availabilityRepository.save(availability);
    }

    //Getting all availabilities
    @GetMapping("/all")
    public List<Availability> getAllAvailabilities(){
       return availabilityRepository.findAll();
    }

    //Getting an availability
    @GetMapping("/{id}")
    public Availability getAnAvailability(@PathVariable Long id){
        return availabilityRepository.findById(id).orElse(null);
    }

    //Delete All Availabilities
    @DeleteMapping("/deleteall")
    public void deleteAllAvailabilities(){
        availabilityRepository.deleteAll();
    }

    //Delete an availability
    @DeleteMapping("/delete/{id}")
    public void deleteAnAvailability(@PathVariable Long id){
        availabilityRepository.deleteById(id);
    }

    //Update an availability
    @PutMapping("/update")
    public Availability updateAnAvailability(@RequestBody Availability availability){
        return availabilityRepository.save(availability);
    }

}
