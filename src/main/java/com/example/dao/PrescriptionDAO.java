package com.example.dao;

import com.example.DTO.DoctorDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.DTO.PrescriptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescriptionDAO {


    public Boolean createPrescription(PrescriptionDTO request) {

        String query = "INSERT INTO prescription ( patient_id, doctor_id,medication,dosage,instructions,duration) VALUES (?, ?,?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setInt(2, request.getDoctorId());
            statement.setString(3, request.getMedication());
            statement.setString(4, request.getDosage());
            statement.setString(5, request.getInstructions());
            statement.setString(6, request.getDuration());



            int rowsInserted = statement.executeUpdate();
            return rowsInserted != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while creating doctor", e);
        }
    }

    public Boolean updatePrescription(Integer id, PrescriptionDTO request) {
        String query = "UPDATE prescription SET patient_id=?,doctor_id=? ,medication=?,dosage=? ,instructions=?,duration=? WHERE prescription_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setInt(2, request.getDoctorId());
            statement.setString(3, request.getMedication());
            statement.setString(4, request.getDosage());
            statement.setString(5, request.getInstructions());
            statement.setString(6, request.getDuration());
            statement.setInt(7,id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while updating perscription", e);
        }
    }
}
