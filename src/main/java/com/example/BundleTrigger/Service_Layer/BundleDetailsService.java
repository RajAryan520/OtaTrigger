package com.example.BundleTrigger.Service_Layer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.BundleTrigger.Model_Layer.VehicleConfigDetails;
import com.example.BundleTrigger.Repository_Layer.BundleDetailsRepository;
import com.example.BundleTrigger.Repository_Layer.VehicleConfigDetailsRepository;


@Service
public class BundleDetailsService {

    private final VehicleConfigDetailsRepository vehiclerepo;
    private final BundleDetailsRepository bundlerepo;
    private VehicleConfigService vehicleservice;

    public BundleDetailsService(VehicleConfigDetailsRepository vehiclerepo, BundleDetailsRepository bundlerepo){
        this.vehiclerepo = vehiclerepo;
        this.bundlerepo = bundlerepo;
    }

    public List<String> getBundle(String battery,String motor, String vin_series, String variant,Date date){

        String vin = vin_series.substring(0,4);
        List<String> bundles = new ArrayList<>();

        Optional<VehicleConfigDetails> res = vehiclerepo.findByBatteryAndMotorAndVinSeriesAndVariant(battery, motor, vin, variant);        
        
        if(res.isPresent()){
            int vehicle_id = res.get().getId();
            bundles = bundlerepo.findBundleByDateAndVehicleConfigId(date,vehicle_id);
        }

        return bundles;
        
    }

    


    
}
