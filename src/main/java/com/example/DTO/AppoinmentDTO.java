package com.example.DTO;

import java.sql.Time;
import java.util.Date;
public class AppoinmentDTO {
    private Integer patientId;
    private Integer doctorId;
    private Date date;
    private Time time;
    private String reason;


    public AppoinmentDTO() {
    }


    public AppoinmentDTO(Integer patientId,Integer doctorId,Date date,Time time,String reason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.reason = reason;

    }
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
