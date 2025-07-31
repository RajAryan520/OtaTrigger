package com.example.BundleTrigger.Controller_Layer;

import java.util.Date;
import java.util.List;

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


    @GetMapping("/BundlesByConfig")
    public ResponseEntity<ApiResponse<List<String>>> bundles(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant,@RequestParam Date date){

        ApiResponse<List<String>> bundle_list = bundleservice.getBundle(battery, motor, vin_series, variant, date);

        return ResponseEntity.status(bundle_list.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(bundle_list);

    }


    @PostMapping("/addBundle")
    public ResponseEntity<ApiResponse<?>> addBundle(@RequestParam String battery, @RequestParam String motor, @RequestParam String vin_series, @RequestParam String variant,@RequestParam Date date,@RequestParam String bundle) {
        
        System.out.println("Endpoint");
        ApiResponse<?> rsp = bundleservice.insertBundle(battery, motor, vin_series, variant, date, bundle);

        return ResponseEntity.status(rsp.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(rsp);

    }

    @DeleteMapping("/deleteBundle")
    public ResponseEntity<ApiResponse<?>> deleteBundle(String uuid ){

        if(!bundlerepo.existsById(uuid)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,"Bundle Not Found",null));
        }
        
        bundlerepo.deleteById(uuid);

        return ResponseEntity.ok(new ApiResponse<>(true,"Bundle Deleted Successfully",null));
 
    }

    @GetMapping("/bundleByUUID")
    public ResponseEntity<ApiResponse<?>> bundleByUUID(@RequestParam String uuid){

        ApiResponse<?> rsp = bundleservice.bundleUUID(uuid);

        return ResponseEntity.status(rsp.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(rsp);

    }

    @GetMapping("/uuidByBundle")
    public ResponseEntity<ApiResponse<?>> uuidByBundle(@RequestParam String bundle){

        ApiResponse<?> rsp = bundleservice.getUUIDByBundle(bundle);
        
        return ResponseEntity.status(rsp.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(rsp);

    }

    // @GetMapping("/UUID")
    // public ResponseEntity<ApiResponse<?>> uuidBundle(@RequestParam String bundle){

    //     String uuid = bundlerepo.FindUUIDByBundle(bundle);


    //     return ResponseEntity.status(rsp.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(rsp);

    // }




    /*
     * get uuid from bundle, for this bundle has to unique -- done
     * Create putmapping, getmapping for uuid 
     */

    
}
  