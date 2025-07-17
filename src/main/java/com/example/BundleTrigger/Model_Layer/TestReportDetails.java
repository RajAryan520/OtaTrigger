package com.example.BundleTrigger.Model_Layer;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_report_details")
public class TestReportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
    private int id;

    @Column(name = "date",nullable = false)
    private Date date;

    @Column(name = "bundle",nullable = false)
    private String bundle;

    @Column(name = "container_name",nullable = false)
    private String container_name;

    @Column(name = "file_name",nullable = false)
    private String file_name;

    
    @jakarta.persistence.Transient
    private String file_link;

    // @PostLoad
    // private void file_link(){
    //     this.file_link =  "https://s3.amazonaws.com/" + container_name + "/" + file_name;
    // }

    public String getFileLink(){
        return file_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getContainer_name() {
        return container_name;
    }

    public void setContainer_name(String container_name) {
        this.container_name = container_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    
}
