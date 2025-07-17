package com.example.BundleTrigger.Repository_Layer;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BundleTrigger.Model_Layer.TestReportDetails;

@Repository
public interface TestReportDetailsRepository extends JpaRepository<TestReportDetails,Integer>{

    Optional<TestReportDetails> findByDateAndBundle(Date date,String bundle);
    
}
