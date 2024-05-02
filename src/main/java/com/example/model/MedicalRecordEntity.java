package com.example.model;

import javax.persistence.*;

@Entity
public class MedicalRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "FK_patient_id"))
    private PatientEntity patientId;

    private String diagnoses;
    private String treatments;
    @Column(name = "other_data")
    private String otherData;

    public MedicalRecordEntity() {
    }

    public MedicalRecordEntity(int recordId, PatientEntity patientId, String diagnoses, String treatments, String otherData) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.otherData = otherData;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public PatientEntity getPatientId() {
        return patientId;
    }

    public void setPatientId(PatientEntity patientId) {
        this.patientId = patientId;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }
}
