package com.example.BundleTrigger.Repository_Layer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BundleTrigger.Model_Layer.BundleDetails;

@Repository
public interface  BundleDetailsRepository extends JpaRepository<BundleDetails, String>{
    

    List<BundleDetails> findBundleByCreatedDateAndVehicleConfigId_Id(Date createdDate,int vehicleConfigId);

    Optional<BundleDetails> findUUIDByBundle(String bundle);

}
