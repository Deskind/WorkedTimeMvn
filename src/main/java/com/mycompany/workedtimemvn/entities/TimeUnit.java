package com.mycompany.workedtimemvn.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class TimeUnit {
    @Id
    private long id;

    @Column
    private Date date;
    @Column
    private String city;
    @Column
    private Date beginDate;
    @Column
    private Date finishDate;
    @Column 
    private Date result;
    
    
    
    
    
    
    
    
    
    
    //Constructor and getters and setters
    
    public TimeUnit() {
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public Date getResult() {
        return result;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setResult(Date result) {
        this.result = result;
    }

    
    
    
}
