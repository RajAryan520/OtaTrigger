package com.example.BundleTrigger.Repository_Layer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BundleTrigger.Model_Layer.BundleDetails;

@Repository
public interface  BundleDetailsRepository extends JpaRepository<BundleDetails, UUID>{
    

    List<String> findBundleByCreatedDateAndVehicleConfigId_Id(Date createdDate,int vehicleConfigId);

    String FindUUIDByBundle(String bundle);

}
