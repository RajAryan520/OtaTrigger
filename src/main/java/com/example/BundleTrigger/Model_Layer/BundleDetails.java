package com.example.BundleTrigger.Model_Layer;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/*

 * uuid (uuid)
 * Date (Date)
 * VehicleConfigID (int)
 * Bundle  (String)
 
 */

 @Entity
 @Table(name="bundle_details")
public class BundleDetails {
    
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="uuid",updatable=false,nullable=false)
    private UUID uuid;
    
    @Column(name="date",nullable=false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="vehicle_config_id",nullable=false)
    private VehicleConfigDetails vehicleConfigId;

    @Column(name="bundle",nullable=false)
    private String bundle;

    
    
    public String getUuid() {
        return uuid.toString();
    }

    public Date getDate(){
        return date;
    }

    public VehicleConfigDetails getVehicleConfigId(){
        return vehicleConfigId;
    }

    public String getBundle(){
        return bundle;
    }


    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setVehicleConfigId(VehicleConfigDetails vehicleConfigId){
        this.vehicleConfigId = vehicleConfigId;
    }

    public void setBundle(String bundle){
        this.bundle = bundle;
    }

}
