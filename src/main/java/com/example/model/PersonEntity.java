package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;
    @Column(name = "name")
    private String name;
    @Column(name = "contact_number")

    private String contactNumber;
    @Column(name = "address")
    private String address;




    public PersonEntity() {
    }

    public PersonEntity(int personId, String name, String contactNumber, String address) {
        this.personId = personId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public PersonEntity(int personId) {
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }
//    @JsonCreator
//    public PersonEntity(@JsonProperty("personId") String personId) {
//        this.personId = Integer.parseInt(personId);
//    }

}
