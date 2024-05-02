package com.example.DTO;

import com.example.model.PersonEntity;

import javax.persistence.Column;

public class PatientDTO {
    private Integer personId;
    private String medicalHistory;
    private String currentHealthStatus;

    private Integer doctorId;

    public PatientDTO() {
    }
    public Integer getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
    public PatientDTO(Integer personId, String medicalHistory, String currentHealthStatus, Integer doctorId) {
        this.personId = personId;
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
        this.doctorId = doctorId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}
