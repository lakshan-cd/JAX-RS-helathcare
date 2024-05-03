package com.example.DTO;

public class DoctorDTO {

    private Integer personId;
    private String specialization;

    public DoctorDTO() {
    }

    public DoctorDTO( Integer personId, String specialization) {

        this.personId = personId;
        this.specialization = specialization;
    }





    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
