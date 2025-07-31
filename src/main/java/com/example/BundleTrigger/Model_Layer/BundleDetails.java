package com.example.BundleTrigger.Model_Layer;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


/*

 * uuid (uuid)
 * Date (Date)
 * VehicleConfigID (int)
 * Bundle  (String)
 
 */

 @Entity
 @Table(name="bundle_details",uniqueConstraints={@UniqueConstraint(columnNames={"created_date","vehicle_config_id","bundle"}),@UniqueConstraint(columnNames = {"bundle"})})
public class BundleDetails {
    
    @Id
    // @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="uuid",updatable=false,nullable=false)
    private String uuid;
    
    @Column(name="created_date",nullable=false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="vehicle_config_id",nullable=false)
    private VehicleConfigDetails vehicleConfigId;

    @Column(name="bundle",nullable=false)
    private String bundle;

    
    
    public String getUuid() {
        return uuid;
    }

    public Date getDate(){
        return createdDate;
    }

    public VehicleConfigDetails getVehicleConfigId(){
        return vehicleConfigId;
    }

    public String getBundle(){
        return bundle;
    }


    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public void setDate(Date createdDate){
        this.createdDate = createdDate;
    }

    public void setVehicleConfigId(VehicleConfigDetails vehicleConfigId){
        this.vehicleConfigId = vehicleConfigId;
    }

    public void setBundle(String bundle){
        this.bundle = bundle;
    }

}
