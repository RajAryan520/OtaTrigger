package com.example.BundleTrigger.Service_Layer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.BundleTrigger.DTO.ApiResponse;
import com.example.BundleTrigger.Model_Layer.BundleDetails;
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

        String vin = vin_series.substring(0,5);
        List<String> bundles = new ArrayList<>();

        Optional<VehicleConfigDetails> res = vehiclerepo.findByBatteryAndMotorAndVinSeriesAndVariant(battery, motor, vin, variant);        
        
        if(res.isPresent()){
            int vehicle_id = res.get().getId();
            bundles = bundlerepo.findBundleByCreatedDateAndVehicleConfigId_Id(date,vehicle_id);
        }

        return bundles;
        
    }

    public ApiResponse<?> insertBundle(String battery,String motor, String vin_series, String variant,Date date,String bundle){

        Optional<VehicleConfigDetails> vehicle_detail = vehiclerepo.findByBatteryAndMotorAndVinSeriesAndVariant(battery, motor, vin_series, variant);
        
        
        if(vehicle_detail.isEmpty()){
            return new ApiResponse<>(false,"Configuration Not Found",null);
        } 
        
        int config_id = vehicle_detail.get().getId();
        
        List<String> existing_bundle = bundlerepo.findBundleByCreatedDateAndVehicleConfigId_Id(date, config_id);

        if(existing_bundle.contains(bundle)){
                return new ApiResponse<>(false,"Bundle already Exist",null);
        }

        BundleDetails new_bundle = new BundleDetails();

        UUID new_uuid = UUID.randomUUID();
        new_bundle.setBundle(bundle);
        new_bundle.setDate(date);
        new_bundle.setVehicleConfigId(vehicle_detail.get());
        new_bundle.setUuid(new_uuid);


        bundlerepo.save(new_bundle);

        return new ApiResponse<>(true,"Bundle Added Successfully",new_bundle.getUuid());

    }


    public ApiResponse<?> bundleUUID(UUID uuid){

        Optional<BundleDetails> bundle = bundlerepo.findById(uuid);
        if(bundle.isEmpty()){
            return new ApiResponse<>(false,"No Bundle Found",null);
        }

        return new ApiResponse<>(true,"Bundle Found Successfully",bundle.get().getBundle());
        
    }

    


    


    
}
