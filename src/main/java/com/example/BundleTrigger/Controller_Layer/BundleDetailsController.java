package com.example.BundleTrigger.Controller_Layer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BundleTrigger.DTO.ApiResponse;
import com.example.BundleTrigger.Repository_Layer.BundleDetailsRepository;
import com.example.BundleTrigger.Service_Layer.BundleDetailsService;


@RestController
public class BundleDetailsController {

    private final BundleDetailsService bundleservice;
    private final BundleDetailsRepository bundlerepo;

    public BundleDetailsController(BundleDetailsService bundleservice,BundleDetailsRepository bundlerepo){
        this.bundleservice = bundleservice;
        this.bundlerepo = bundlerepo;
    }


    @GetMapping("/Bundles")
    public ResponseEntity<ApiResponse<List<String>>> bundles(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant,@RequestParam Date date){

        List<String> rsp = bundleservice.getBundle(battery, motor, vin_series, variant, date);

        if(!rsp.isEmpty()){
            return ResponseEntity.ok(new ApiResponse<>(true,"Bundle Found",rsp));
        }
        else{
            String error = "No Bundle Found"; 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,error,rsp));
        }

    }


    @PostMapping("/addBundle")
    public ResponseEntity<ApiResponse<?>> addBundle(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant,@RequestParam Date date) {
    
        ApiResponse<?> rsp = bundleservice.insertBundle(battery, motor, vin_series, variant, date, motor);

        return ResponseEntity.status(rsp.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(rsp);

    }

    @DeleteMapping("/deleteBundle")
    public ResponseEntity<ApiResponse<?>> deleteBundle(UUID uuid ){

        if(!bundlerepo.existsById(uuid)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,"Bundle Not Found",null));
        }
        
        bundlerepo.deleteById(uuid);

        return ResponseEntity.ok(new ApiResponse<>(true,"Bundle Deleted Successfully",null));
 
    }


    /*
     * get uuid from bundle, for this bundle has to unique
     * Create putmapping, getmapping for uuid
     */

    
}
  