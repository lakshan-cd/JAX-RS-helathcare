package com.example.dao;

import com.example.DTO.AppoinmentDTO;
import com.example.DTO.DoctorDTO;
import com.example.DTO.MedicalRecordDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicalRecordDAO {
    public Boolean createMedicalRecord(MedicalRecordDTO request) {

        String query = "INSERT INTO medicalrecord ( patient_id, diagnoses,treatments,other_data) VALUES (?, ?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setString(2, request.getDiagnoses());
            statement.setString(3, request.getTreatments());
            statement.setString(4, request.getOtherData());


            int rowsInserted = statement.executeUpdate();
            return rowsInserted != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while creating medical record", e);
        }
    }


    public Boolean updateMedicalRecord(Integer id, MedicalRecordDTO request) {
        String query = "UPDATE medicalrecord SET patient_id=?,diagnoses=? ,treatments=?,other_data=? WHERE record_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setString(2, request.getDiagnoses());
            statement.setString(3, request.getTreatments());
            statement.setString(4, request.getOtherData());
            statement.setInt(5,id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while updating medical record", e);
        }
    }

    public Boolean deleteMedicalRecord(int id) {
        String query = "DELETE FROM medicalrecord WHERE record_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int result = statement.executeUpdate();
            if (result == 0){
                return false;
            }else {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while deleting appointment", e);
        }
    }
}
