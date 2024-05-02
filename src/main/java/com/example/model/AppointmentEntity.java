package com.example.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "FK_patient_id"))
    private PersonEntity patientId;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "FK_doctor_id"))
    private DoctorEntity doctorId;

    private Date date;
    private Time time;
    private String reason;

    public AppointmentEntity() {
    }

    public AppointmentEntity(int appointmentId, PersonEntity patientId, DoctorEntity doctorId, Date date, Time time, String reason) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public PersonEntity getPatientId() {
        return patientId;
    }

    public void setPatientId(PersonEntity patientId) {
        this.patientId = patientId;
    }

    public DoctorEntity getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorEntity doctorId) {
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
