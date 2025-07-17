package com.example.BundleTrigger.Service_Layer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.BundleTrigger.Model_Layer.TestReportDetails;
import com.example.BundleTrigger.Repository_Layer.TestReportDetailsRepository;

public class TestReportDetailsService {

    private final TestReportDetailsRepository reportrepo;

    public TestReportDetailsService(TestReportDetailsRepository reportrepo){
        this.reportrepo = reportrepo;
    }


    public void uploadAndSaveMetadata(String fileName,String containerName,String bundle,Date date){
        TestReportDetails report = new TestReportDetails();
        report.setFile_name(fileName);
        report.setContainer_name(containerName);
        report.setBundle(bundle);
        report.setDate(date);

        reportrepo.save(report);

    }


    public List<String> getFileName(Date date, String bundle){
        Optional<TestReportDetails> url = reportrepo.findByDateAndBundle(date, bundle);
        List<String> res = new ArrayList<>();

        if(url.isPresent()){
            res.add(url.get().getFile_name());
        }

        return res;

    }

    public String preSignedReportLink(String containerName, String FileName, int expiryMinute){
        
        String preSignedUrl = "https://" + containerName + ".s3.amazonaws.com/" + FileName;

        return preSignedUrl;
    }

}
