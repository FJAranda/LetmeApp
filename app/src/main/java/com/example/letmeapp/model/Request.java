package com.example.letmeapp.model;

import java.util.Date;

public class Request {
    private String item;
    private String applicant;
    private String message;
    private Date startDate;
    private Date endDate;
    private String status;

    public Request(String item, String applicant, String message, Date startDate, Date endDate, String status) {
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
}
