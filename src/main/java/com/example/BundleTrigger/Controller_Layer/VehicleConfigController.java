package com.example.BundleTrigger.Controller_Layer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BundleTrigger.DTO.ApiResponse;
import com.example.BundleTrigger.Repository_Layer.VehicleConfigDetailsRepository;
import com.example.BundleTrigger.Service_Layer.VehicleConfigService;

@RestController
public class VehicleConfigController {

    private final VehicleConfigService vehicleservice;
    private final VehicleConfigDetailsRepository vehiclerepo;
    
    public VehicleConfigController(VehicleConfigService vehicleservice, VehicleConfigDetailsRepository vehiclerepo){
        this.vehicleservice = vehicleservice;
        this.vehiclerepo = vehiclerepo;
    }


    /*
     * CRUD api's for vehicle config table
     * 
     * 
     * 
     */


    @PostMapping("/addVehicleConfig")
    public ResponseEntity<ApiResponse<Integer>> addVehicleConfig(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant){


        int Config_id = vehicleservice.insertVehicleConfig(battery, motor, vin_series, variant);
        return ResponseEntity.ok(new ApiResponse<>(true,"New Configuration Added",Config_id));

    }

    @DeleteMapping("/DeleteVehicleConfig")
    public ResponseEntity<ApiResponse<?>> deleteVehicleConfig(@RequestParam int configId){

        if(!vehiclerepo.existsById(configId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,"Configuration Not Found",null));
        }
        
        vehiclerepo.deleteById(configId);

        return ResponseEntity.ok(new ApiResponse<>(true,"Configuration Deleted Successfully",null));

    }

    @PutMapping("/UpdateVehicleConfig")
    public ResponseEntity<ApiResponse<?>> updateVehicleConfig(@RequestParam int configId,@RequestParam String new_battery, @RequestParam String new_motor, @RequestParam String new_vin_series, @RequestParam String new_variant){

            ApiResponse<?> response = vehicleservice.updateVehicleConfig(configId, new_battery, new_motor, new_vin_series, new_variant);

            return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);

    }


    
    @GetMapping("/VehicleConfigId")
    public ResponseEntity<Map<String, Object>> vehicleConfigId(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant){

        Optional<Integer> vehcile_id = vehicleservice.isValidConfig(battery, motor, vin_series, variant);

        Map<String,Object> rsp = new HashMap<>();
        if(vehcile_id.isPresent()){
            rsp.put("VehicleConfigId ", vehcile_id.get());
            return ResponseEntity.ok(rsp);                      // Wrap the rsp to map for json formatting,  for large project use DTO class .
        }
        else{
            rsp.put("Error", "Configuration not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rsp);   
        }
        
    }





    @GetMapping("/VehicleConfigIdv1")
    public ResponseEntity<ApiResponse<Optional<Integer>>> vehicleConfigIdv1(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant){

        Optional<Integer> vehcile_id = vehicleservice.isValidConfig(battery, motor, vin_series, variant);

        if(vehcile_id.isPresent()){

            return ResponseEntity.ok(new ApiResponse<>(true,"Configuration Found",vehcile_id)); // uses DTO generic api response refer to ApiResponse class
        
        }
        else{
            String error = "Configuration not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,error,vehcile_id));   
        }
        
    }





    
}
