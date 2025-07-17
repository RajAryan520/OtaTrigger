package com.example.BundleTrigger.Controller_Layer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BundleTrigger.DTO.ApiResponse;
import com.example.BundleTrigger.Service_Layer.VehicleConfigService;

@RestController
public class VehicleConfigController {

    private final VehicleConfigService vehicleservice;
    
    public VehicleConfigController(VehicleConfigService vehicleservice){
        this.vehicleservice = vehicleservice;
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
