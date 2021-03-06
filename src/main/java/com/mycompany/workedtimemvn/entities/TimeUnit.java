package com.mycompany.workedtimemvn.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TimeUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String date;
    @Column
    private String city;
    @Column
    private String beginTime;
    @Column
    private String finishTime;
    @Column 
    private String result;
    @Column(columnDefinition = "enum('BUSINESS_TRIP','ON_PLACE')")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;
    
    
    
    
    
    
    
    
    
    
    
    //Constructor and getters and setters

    public TimeUnit() {
    }

    public TimeUnit(long id, String date, String city, String beginTime, String finishTime, String result, UnitType unitType) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.beginTime = beginTime;
        this.finishTime = finishTime;
        this.result = result;
        this.unitType = unitType;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public String getResult() {
        return result;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }
    
    
}
