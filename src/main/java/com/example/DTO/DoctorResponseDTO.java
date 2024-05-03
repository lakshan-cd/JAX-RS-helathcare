package com.example.DTO;

public class DoctorResponseDTO {
    private Integer doctorId;
    private String specialization;
    private String name;
    private String contactNumber;
    private String address;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public DoctorResponseDTO(Integer doctorId, String specialization, String name, String contactNumber, String address) {
//        this.doctorId = doctorId;
//        this.specialization = specialization;
//        this.name = name;
//        this.contactNumber = contactNumber;
//        this.address = address;
//    }
}
