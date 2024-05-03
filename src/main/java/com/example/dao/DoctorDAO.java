package com.example.dao;

import com.example.DTO.DoctorDTO;
import com.example.DTO.DoctorResponseDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.model.DoctorEntity;
import com.example.model.PatientEntity;
import com.example.model.PersonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorDAO {
    private PersonDAO personDAO = new PersonDAO();
    public Boolean createDoctor(DoctorDTO request) {

        String query = "INSERT INTO doctor ( person_id, specialization) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPersonId());
            statement.setString(2, request.getSpecialization());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while creating doctor", e);
        }
    }

    public Boolean updateDoctor(Integer id,DoctorDTO request) {
        String query = "UPDATE doctor SET person_id=?, specialization=? WHERE doctor_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPersonId());
            statement.setString(2, request.getSpecialization());
            statement.setInt(3,id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while updating person", e);
        }
    }


    public Boolean deleteDoctor(int id) {
        String query = "DELETE FROM doctor WHERE doctor_id=?";

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
            throw new DAOException("Error occurred while deleting person", e);
        }
    }

    public List<Map<String, Object>> getAllDoctorsWithPatients() {
        List<Map<String, Object>> doctorsWithPatients = new ArrayList<>();
        String query = "SELECT d.*, p.* " +
                "FROM doctor d " +
                "LEFT JOIN patient p ON d.doctor_id = p.doctor_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            Map<DoctorEntity, List<PatientEntity>> doctorMap = new HashMap<>();
            while (resultSet.next()) {
                int doctorId = resultSet.getInt("d.doctor_id");
                int personId = resultSet.getInt("d.person_id");
                String specialization = resultSet.getString("d.specialization");

                // Assuming you have a method to retrieve PersonEntity by ID
                PersonEntity doctorPerson = personDAO.getPersonById(personId);
                DoctorEntity doctor = new DoctorEntity(doctorId, doctorPerson, specialization);

                int patientId = resultSet.getInt("p.patient_id");
                personId = resultSet.getInt("p.person_id");
                String medicalHistory = resultSet.getString("p.medical_history");
                String currentHealthStatus = resultSet.getString("p.current_health_status");

                // Assuming you have a method to retrieve PersonEntity by ID
                PersonEntity patientPerson = personDAO.getPersonById(personId);
                PatientEntity patient = new PatientEntity(patientId, patientPerson, null, medicalHistory, currentHealthStatus);

                if (!doctorMap.containsKey(doctor)) {
                    doctorMap.put(doctor, new ArrayList<>());
                }
                doctorMap.get(doctor).add(patient);
            }

            for (Map.Entry<DoctorEntity, List<PatientEntity>> entry : doctorMap.entrySet()) {
                DoctorEntity doctor = entry.getKey();
                List<PatientEntity> patients = entry.getValue();

                Map<String, Object> doctorWithPatients = new HashMap<>();
                doctorWithPatients.put("doctor", doctor);
                doctorWithPatients.put("patients", patients);
                doctorsWithPatients.add(doctorWithPatients);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving doctors with patients", e);
        }

        return doctorsWithPatients;
    }
    public Map<String, Object> getDoctorByIdWithPatients(int doctorId) {
        Map<String, Object> doctorWithPatients = new HashMap<>();
        String query = "SELECT d.*, p.* " +
                "FROM doctor d " +
                "LEFT JOIN patient p ON d.doctor_id = p.doctor_id " +
                "WHERE d.doctor_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();

            List<Map<String, Object>> patients = new ArrayList<>();
            DoctorEntity doctor = null;

            while (resultSet.next()) {
                if (doctor == null) {
                    int personId = resultSet.getInt("d.person_id");
                    String specialization = resultSet.getString("d.specialization");

                    // Assuming you have a method to retrieve PersonEntity by ID
                    PersonEntity doctorPerson = personDAO.getPersonById(personId);
                    doctor = new DoctorEntity(doctorId, doctorPerson, specialization);
                }

                int patientId = resultSet.getInt("p.patient_id");
                int personId = resultSet.getInt("p.person_id");
                String medicalHistory = resultSet.getString("p.medical_history");
                String currentHealthStatus = resultSet.getString("p.current_health_status");

                Map<String, Object> patientMap = new HashMap<>();
                patientMap.put("patientId", patientId);
                patientMap.put("personId", personDAO.getPersonById(personId)); // Assuming you have a method to retrieve PersonEntity by ID
                patientMap.put("medicalHistory", medicalHistory);
                patientMap.put("currentHealthStatus", currentHealthStatus);

                patients.add(patientMap);
            }

            doctorWithPatients.put("doctor", doctor);
            doctorWithPatients.put("patients", patients);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving doctor with patients by ID", e);
        }

        return doctorWithPatients;
    }




}
