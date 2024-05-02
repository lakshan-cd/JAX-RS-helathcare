package com.example.dao;

import com.example.application.DatabaseConnection;
import com.example.DTO.PatientDTO;
import com.example.exception.DAOException;
import com.example.model.DoctorEntity;
import com.example.model.PatientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientDAO {

    public Boolean createPatient(PatientDTO request) {
        String query = "INSERT INTO patient (person_id, medical_history, current_health_status,doctor_id) VALUES (?, ?, ?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,request.getPersonId());
            statement.setString(2, request.getMedicalHistory());
            statement.setString(3, request.getCurrentHealthStatus());
            statement.setInt(4, request.getDoctorId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while creating patient", e);
        }
    }

    public List<PatientEntity> getAllPatients() {
        List<PatientEntity> patients = new ArrayList<>();

        String query = "SELECT p.*, d.* FROM patient p " +
                "LEFT JOIN doctor d ON p.doctor_id = d.doctor_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PatientEntity patient = new PatientEntity();
                patient.setPatientId(resultSet.getInt("patient_id"));
//                patient.setPersonId(resultSet.getInt("person_id"));
                patient.setMedicalHistory(resultSet.getString("medical_history"));
                patient.setCurrentHealthStatus(resultSet.getString("current_health_status"));
                // Populate other patient fields as needed

                DoctorEntity doctor = new DoctorEntity();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                // Populate other doctor fields as needed

                // Set doctor details to patient
                patient.setDoctorId(doctor);

                patients.add(patient);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving patients", e);
        }

        return patients;
    }
}
