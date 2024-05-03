package com.example.DTO;

public class MedicalRecordDTO {

    private int patientId;
    private String diagnoses;
    private String treatments;
    private String otherData;

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(int recordId, int patientId, String diagnoses, String treatments, String otherData) {
        this.patientId = patientId;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.otherData = otherData;
    }



    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
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
