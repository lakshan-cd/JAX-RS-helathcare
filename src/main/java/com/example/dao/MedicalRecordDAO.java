package com.example.dao;

import com.example.DTO.AppoinmentDTO;
import com.example.DTO.DoctorDTO;
import com.example.DTO.MedicalRecordDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.model.PersonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> getAllMedicalRecordsWithPatients() {
        List<Map<String, Object>> medicalRecords = new ArrayList<>();
        String query = "SELECT mr.*, p1.*, p2.* " +
                "FROM medicalrecord mr " +
                "LEFT JOIN patient p1 ON mr.patient_id = p1.patient_id " +
                "LEFT JOIN person p2 ON p1.person_id = p2.person_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int recordId = resultSet.getInt("mr.record_id");
                String diagnoses = resultSet.getString("mr.diagnoses");
                String treatments = resultSet.getString("mr.treatments");
                String otherData = resultSet.getString("mr.other_data");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setPersonId(resultSet.getInt("p1.person_id"));
                patient.setName(resultSet.getString("p2.name"));
                patient.setContactNumber(resultSet.getString("p2.contact_number"));
                patient.setAddress(resultSet.getString("p2.address"));

                // Construct medical record map
                Map<String, Object> medicalRecordMap = new HashMap<>();
                medicalRecordMap.put("recordId", recordId);
                medicalRecordMap.put("diagnoses", diagnoses);
                medicalRecordMap.put("treatments", treatments);
                medicalRecordMap.put("otherData", otherData);
                medicalRecordMap.put("patient", patient);

                medicalRecords.add(medicalRecordMap);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving medical records with patients", e);
        }

        return medicalRecords;
    }

    public Map<String, Object> getMedicalRecordByIdWithPatient(int recordId) {
        Map<String, Object> medicalRecordMap = null;
        String query = "SELECT mr.*, p1.*, p2.* " +
                "FROM medicalrecord mr " +
                "LEFT JOIN patient p1 ON mr.patient_id = p1.patient_id " +
                "LEFT JOIN person p2 ON p1.person_id = p2.person_id " +
                "WHERE mr.record_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String diagnoses = resultSet.getString("mr.diagnoses");
                String treatments = resultSet.getString("mr.treatments");
                String otherData = resultSet.getString("mr.other_data");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setPersonId(resultSet.getInt("p1.person_id"));
                patient.setName(resultSet.getString("p2.name"));
                patient.setContactNumber(resultSet.getString("p2.contact_number"));
                patient.setAddress(resultSet.getString("p2.address"));

                // Construct medical record map
                medicalRecordMap = new HashMap<>();
                medicalRecordMap.put("recordId", recordId);
                medicalRecordMap.put("diagnoses", diagnoses);
                medicalRecordMap.put("treatments", treatments);
                medicalRecordMap.put("otherData", otherData);
                medicalRecordMap.put("patient", patient);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving medical record with patient by ID", e);
        }

        return medicalRecordMap;
    }

}
