package com.availability.availability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.availability.availability.model.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long>{
    
}
