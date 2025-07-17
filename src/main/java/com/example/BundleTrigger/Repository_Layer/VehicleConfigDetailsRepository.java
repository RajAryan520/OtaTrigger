package com.example.BundleTrigger.Repository_Layer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BundleTrigger.Model_Layer.VehicleConfigDetails;

@Repository
public interface VehicleConfigDetailsRepository extends JpaRepository<VehicleConfigDetails, Integer>{
    

    Optional<VehicleConfigDetails> findByBatteryAndMotorAndVinSeriesAndVariant(String battery, String motor, String vinSeries, String variant);
    
}