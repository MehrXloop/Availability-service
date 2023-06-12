package com.availability.availability.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.availability.availability.model.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long>{

      List<Availability> findBycounselorId(Long counselor_id);
    
}
