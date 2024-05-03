package com.example.dao;

import com.example.DTO.DoctorDTO;
import com.example.DTO.DoctorResponseDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.DTO.PrescriptionDTO;
import com.example.model.DoctorEntity;
import com.example.model.PersonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Boolean deletePrescription(int id) {
        String query = "DELETE FROM prescription WHERE prescription_id=?";

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
            throw new DAOException("Error occurred while deleting perscription", e);
        }
    }

    public List<Map<String, Object>> getAllPrescriptionsWithPatientsAndDoctors() {
        List<Map<String, Object>> prescriptions = new ArrayList<>();
        String query = "SELECT pr.*, p1.*, d1.*, p2.*, d2.*, dp.*, dd.* " +
                "FROM prescription pr " +
                "LEFT JOIN patient p1 ON pr.patient_id = p1.patient_id " +
                "LEFT JOIN doctor d1 ON pr.doctor_id = d1.doctor_id " +
                "LEFT JOIN person p2 ON p1.person_id = p2.person_id " +
                "LEFT JOIN person d2 ON d1.person_id = d2.person_id " +
                "LEFT JOIN person dp ON d1.person_id = dp.person_id " +
                "LEFT JOIN person dd ON d1.person_id = dd.person_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int prescriptionId = resultSet.getInt("pr.prescription_id");
                String medication = resultSet.getString("pr.medication");
                String dosage = resultSet.getString("pr.dosage");
                String instructions = resultSet.getString("pr.instructions");
                String duration = resultSet.getString("pr.duration");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setPersonId(resultSet.getInt("p1.person_id"));
                patient.setName(resultSet.getString("p2.name"));
                patient.setContactNumber(resultSet.getString("p2.contact_number"));
                patient.setAddress(resultSet.getString("p2.address"));

                // Construct doctor entity
                DoctorResponseDTO doctor = new DoctorResponseDTO();
                doctor.setDoctorId(resultSet.getInt("d1.doctor_id"));
                doctor.setSpecialization(resultSet.getString("d1.specialization"));

                // Include doctor's personal details from associated PersonEntity
                doctor.setName(resultSet.getString("dd.name"));
                doctor.setContactNumber(resultSet.getString("dd.contact_number"));
                doctor.setAddress(resultSet.getString("dd.address"));

                // Construct prescription map
                Map<String, Object> prescriptionMap = new HashMap<>();
                prescriptionMap.put("prescriptionId", prescriptionId);
                prescriptionMap.put("medication", medication);
                prescriptionMap.put("dosage", dosage);
                prescriptionMap.put("instructions", instructions);
                prescriptionMap.put("duration", duration);
                prescriptionMap.put("patient", patient);
                prescriptionMap.put("doctor", doctor);

                prescriptions.add(prescriptionMap);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving prescriptions with patients and doctors", e);
        }

        return prescriptions;
    }

    public Map<String, Object> getPrescriptionByIdWithPatientAndDoctor(int prescriptionId) {
        Map<String, Object> prescriptionMap = null;
        String query = "SELECT pr.*, p1.*, d1.*, p2.*, d2.*, dp.*, dd.* " +
                "FROM prescription pr " +
                "LEFT JOIN patient p1 ON pr.patient_id = p1.patient_id " +
                "LEFT JOIN doctor d1 ON pr.doctor_id = d1.doctor_id " +
                "LEFT JOIN person p2 ON p1.person_id = p2.person_id " +
                "LEFT JOIN person d2 ON d1.person_id = d2.person_id " +
                "LEFT JOIN person dp ON d1.person_id = dp.person_id " +
                "LEFT JOIN person dd ON d1.person_id = dd.person_id " +
                "WHERE pr.prescription_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, prescriptionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String medication = resultSet.getString("pr.medication");
                String dosage = resultSet.getString("pr.dosage");
                String instructions = resultSet.getString("pr.instructions");
                String duration = resultSet.getString("pr.duration");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setPersonId(resultSet.getInt("p1.person_id"));
                patient.setName(resultSet.getString("p2.name"));
                patient.setContactNumber(resultSet.getString("p2.contact_number"));
                patient.setAddress(resultSet.getString("p2.address"));

                // Construct doctor entity
                DoctorResponseDTO doctor = new DoctorResponseDTO();
                doctor.setDoctorId(resultSet.getInt("d1.doctor_id"));
                doctor.setSpecialization(resultSet.getString("d1.specialization"));

                // Include doctor's personal details from associated PersonEntity
                doctor.setName(resultSet.getString("dd.name"));
                doctor.setContactNumber(resultSet.getString("dd.contact_number"));
                doctor.setAddress(resultSet.getString("dd.address"));

                // Construct prescription map
                prescriptionMap = new HashMap<>();
                prescriptionMap.put("prescriptionId", prescriptionId);
                prescriptionMap.put("medication", medication);
                prescriptionMap.put("dosage", dosage);
                prescriptionMap.put("instructions", instructions);
                prescriptionMap.put("duration", duration);
                prescriptionMap.put("patient", patient);
                prescriptionMap.put("doctor", doctor);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving prescription with patient and doctor by ID", e);
        }

        return prescriptionMap;
    }


}
