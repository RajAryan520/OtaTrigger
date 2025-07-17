package com.example.BundleTrigger.Service_Layer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BundleTrigger.Model_Layer.VehicleConfigDetails;
import com.example.BundleTrigger.Repository_Layer.BundleDetailsRepository;
import com.example.BundleTrigger.Repository_Layer.VehicleConfigDetailsRepository;



@Service
public class VehicleConfigService {
    
    
    private final VehicleConfigDetailsRepository vehiclerepo;
    private final BundleDetailsRepository bundlerepo;

    @Autowired
    public VehicleConfigService(VehicleConfigDetailsRepository vehiclerepo,BundleDetailsRepository bundlerepo){
        this.vehiclerepo = vehiclerepo;
        this.bundlerepo = bundlerepo;
    }

    public Optional<Integer> isValidConfig(String battery,String motor, String vin_series, String variant){

        String vin = vin_series.substring(0, 4);

        Optional<VehicleConfigDetails> result = vehiclerepo.findByBatteryAndMotorAndVinSeriesAndVariant(battery, motor, vin, variant);

        return result.map(VehicleConfigDetails::getId);
        
    }



}
