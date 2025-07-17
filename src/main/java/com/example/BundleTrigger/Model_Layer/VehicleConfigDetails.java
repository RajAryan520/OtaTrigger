package com.example.BundleTrigger.Model_Layer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Id (int)
 * Battery (String)
 * Motor (String)
 * VinSeries (String)
 * Variant (String)
 */

@Entity
@Table(name="vehicle_config_details")
public class VehicleConfigDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private int id;

    @Column(name="battery",nullable=false)
    private String battery;

    @Column(name="motor",nullable=false)
    private String motor;

    @Column(name="",nullable=false)
    private String vinSeries;

    @Column(name="variant",nullable=false)
    private String variant;


    public int getId(){
        return id;
    }

    public String getBattery(){
        return battery;
    }

    public String getMotor(){
        return motor;
    }

    public String getVinSeries(){
        return vinSeries;
    }

    public String getVariant(){
        return variant;
    }


    public void setId(int id){
        this.id = id;
    }

    public void setBattery(String battery){
        this.battery = battery;
    }

    public void setMotor(String motor){
        this.motor = motor;
    }

    public void setVinSeries(String vinSeries){
        this.vinSeries = vinSeries;
    }

    public void setVariant(String variant){
        this.variant = variant;
    }

    
}
