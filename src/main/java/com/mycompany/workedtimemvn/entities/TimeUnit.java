package com.mycompany.workedtimemvn.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class TimeUnit {
    @Id
    private long id;

    @Column
    private String city;
    
    
    
    //Constructor and getters and setters
    
    public TimeUnit() {
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
