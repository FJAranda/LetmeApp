package com.example.letmeapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Request implements Comparable, Serializable {
    public static final String TAG = "request";
    //FIRESTORE COLLECTION NAMES
    public static final String LETREQUEST_FIRESTORE = "letrequests";
    public static final String RETURNREQUEST_FIRESTORE = "returnrequests";
    public static final String REQUEST_STATUS = "status";
    public static final String REQUEST_STATUS_SENDED = "sended";
    public static final String REQUEST_STATUS_NOTIFIED = "sended";
    public static final String REQUEST_ITEM = "item";

    @PrimaryKey(autoGenerate = true)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    private String item;
    @NonNull
    private String applicant;
    private String message;
    @NonNull
    private Date startDate;
    private Date endDate;
    @NonNull
    private String status;

    @Ignore
    public Request(String item, String applicant, String message, Date startDate, Date endDate, String status) {
        this.item = item;
        this.applicant = applicant;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Request(int id, @NonNull String item, @NonNull String applicant, String message, @NonNull Date startDate, Date endDate, @NonNull String status) {
        this.id = id;
        this.item = item;
        this.applicant = applicant;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        return ((Request)o).getStartDate().equals(getStartDate());
    }

    @Override
    public int compareTo(Object o) {
        if (((Request)o).getStartDate().equals(getStartDate())){
            return((Request)o).getEndDate().compareTo(getEndDate());
        }else{
            return ((Request)o).getStartDate().compareTo(getStartDate());
        }
    }
}
