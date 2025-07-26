package com.example.BundleTrigger.Service_Layer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BundleTrigger.DTO.ApiResponse;
import com.example.BundleTrigger.Exception.DuplicateConfigException;
import com.example.BundleTrigger.Model_Layer.VehicleConfigDetails;
import com.example.BundleTrigger.Repository_Layer.BundleDetailsRepository;
import com.example.BundleTrigger.Repository_Layer.VehicleConfigDetailsRepository;



@Service
public class VehicleConfigService {
    
    
    private final VehicleConfigDetailsRepository vehiclerepo;
    // private VehicleConfigDetails vehicleConfigDetails;
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


    /*
     * [id , battery, motor, vin_series, variant]
     * ["2.5" , "7" , "P53BH" , "M3X"] , ["3.5" , "7" , "P53BJ" , "M3X"] , ["4.5" , "7" , "P53BK" , "M3X"] , ["4.5" , "11" , "P53BX" , "M3X+"]
     * ["2" , "7" , "P53BW" , "S1X"] , ["3" , "7" , "P53BV" , S1X"] , ["4" , "7" , "P53BU" , "S1X"] , ["4" , "11" , "P53BT" , "S1X+"]
     * 
     * CONTROLLER -> SERVICE LAYER -> MODEL LAYER 
     * 
     */

    public int insertVehicleConfig(String battery,String motor,String vin_series, String variant){

        Optional<VehicleConfigDetails> check_entry = vehiclerepo.findByBatteryAndMotorAndVinSeriesAndVariant(battery, motor, vin_series, variant);

        if(check_entry.isPresent()){
            throw new DuplicateConfigException("Configuration already Exist");
        }

        VehicleConfigDetails new_entry = new VehicleConfigDetails();
        new_entry.setBattery(battery);
        new_entry.setMotor(motor);
        new_entry.setVinSeries(vin_series);
        new_entry.setVariant(variant);

        VehicleConfigDetails saved_entry =  vehiclerepo.save(new_entry);

        return saved_entry.getId(); 
    }


    public ApiResponse<?> updateVehicleConfig(int configId,String new_battery, String new_motor, String new_vin_series, String new_variant){

            Optional<VehicleConfigDetails> new_config = vehiclerepo.findByBatteryAndMotorAndVinSeriesAndVariant(new_battery, new_motor, new_vin_series, new_variant);
            
            Optional<VehicleConfigDetails> existingConfig = vehiclerepo.findById(configId);

            if(!existingConfig.isPresent()){
                return new ApiResponse<>(false,"No Configuration Found by Given Id",null);
            }

            if(new_config.isPresent()){
                throw new DuplicateConfigException("Configuration Already Exist");
            }

            VehicleConfigDetails config = existingConfig.get();
            config.setBattery(new_battery);
            config.setMotor(new_motor);
            config.setVariant(new_variant);
            config.setVinSeries(new_vin_series);

            vehiclerepo.save(config);

            return new ApiResponse<>(true,"Configuration Updated Successfully",new_config);
    }

    



     



}
