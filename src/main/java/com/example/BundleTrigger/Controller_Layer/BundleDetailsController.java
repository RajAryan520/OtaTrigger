package com.example.BundleTrigger.Controller_Layer;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BundleTrigger.DTO.ApiResponse;
import com.example.BundleTrigger.Service_Layer.BundleDetailsService;


@RestController
public class BundleDetailsController {

    private final BundleDetailsService bundleserive;

    public BundleDetailsController(BundleDetailsService bundleservice){
        this.bundleserive = bundleservice;
    }


    @GetMapping("/Bundles")
    public ResponseEntity<ApiResponse<List<String>>> bundles(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String varian,@RequestParam Date date){

        List<String> rsp = bundleserive.getBundle(battery, motor, vin_series, varian, date);

        if(!rsp.isEmpty()){
            return ResponseEntity.ok(new ApiResponse<>(true,"Bundle Found",rsp));
        }
        else{
            String error = "No Bundle Found on Given Date";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,error,rsp));
        }

    }
    
}
