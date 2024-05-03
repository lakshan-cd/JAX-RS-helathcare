package com.example.dao;

import com.example.DTO.AppoinmentDTO;
import com.example.DTO.DoctorDTO;
import com.example.DTO.PrescriptionDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.model.DoctorEntity;
import com.example.model.PersonEntity;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class AppointmentDAO {

    public Boolean createAppointment(AppoinmentDTO request) {

        String query = "INSERT INTO appointment ( patient_id, doctor_id, date,time,reason) VALUES (?, ?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setInt(2, request.getDoctorId());
            java.sql.Date sqlDate = new java.sql.Date(request.getDate().getTime());
            statement.setDate(3, sqlDate);

            // Use java.sql.Time directly
            statement.setTime(4, request.getTime());
            statement.setString(5, request.getReason());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while creating doctor", e);
        }
    }

    public Boolean updateAppointment(Integer id, AppoinmentDTO request) {
        String query = "UPDATE appointment SET patient_id=?,doctor_id=? ,date=?,time=?,reason=? WHERE appointment_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setInt(2, request.getDoctorId());
            java.sql.Date sqlDate = new java.sql.Date(request.getDate().getTime());
            statement.setDate(3, sqlDate);

            // Use java.sql.Time directly
            statement.setTime(4, request.getTime());
            statement.setString(5, request.getReason());
            statement.setInt(6,id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while updating appointment", e);
        }
    }

    public Boolean deleteAppointment(int id) {
        String query = "DELETE FROM appointment WHERE appointment_id=?";

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

    public List<Map<String, Object>> getAllAppointmentsWithPatientsAndDoctors() {
        List<Map<String, Object>> appointments = new ArrayList<>();
        String query = "SELECT a.*, p1.*, d1.*, p2.*, d2.* " +
                "FROM appointment a " +
                "LEFT JOIN patient p1 ON a.patient_id = p1.patient_id " +
                "LEFT JOIN doctor d1 ON a.doctor_id = d1.doctor_id " +
                "LEFT JOIN person p2 ON p1.person_id = p2.person_id " +
                "LEFT JOIN person d2 ON d1.person_id = d2.person_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("a.appointment_id");
                Date date = resultSet.getDate("a.date");
                Time time = resultSet.getTime("a.time");
                String reason = resultSet.getString("a.reason");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setPersonId(resultSet.getInt("p1.person_id"));
                patient.setName(resultSet.getString("p2.name"));
                patient.setContactNumber(resultSet.getString("p2.contact_number"));
                patient.setAddress(resultSet.getString("p2.address"));

                // Construct doctor entity
                DoctorEntity doctor = new DoctorEntity();
                doctor.setDoctorId(resultSet.getInt("d1.doctor_id"));
                doctor.setSpecialization(resultSet.getString("d1.specialization"));

                // Construct appointment map
                Map<String, Object> appointment = new HashMap<>();
                appointment.put("appointmentId", appointmentId);
                appointment.put("date", date);
                appointment.put("time", time);
                appointment.put("reason", reason);
                appointment.put("patient", patient);
                appointment.put("doctor", doctor);

                appointments.add(appointment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving appointments with patients and doctors", e);
        }

        return appointments;
    }

    public Map<String, Object> getAppointmentByIdWithPatientAndDoctor(int appointmentId) {
        Map<String, Object> appointment = null;
        String query = "SELECT a.*, p1.*, d1.*, p2.*, d2.* " +
                "FROM appointment a " +
                "LEFT JOIN patient p1 ON a.patient_id = p1.patient_id " +
                "LEFT JOIN doctor d1 ON a.doctor_id = d1.doctor_id " +
                "LEFT JOIN person p2 ON p1.person_id = p2.person_id " +
                "LEFT JOIN person d2 ON d1.person_id = d2.person_id " +
                "WHERE a.appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Date date = resultSet.getDate("a.date");
                Time time = resultSet.getTime("a.time");
                String reason = resultSet.getString("a.reason");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setPersonId(resultSet.getInt("p1.person_id"));
                patient.setName(resultSet.getString("p2.name"));
                patient.setContactNumber(resultSet.getString("p2.contact_number"));
                patient.setAddress(resultSet.getString("p2.address"));

                // Construct doctor entity
                DoctorEntity doctor = new DoctorEntity();
                doctor.setDoctorId(resultSet.getInt("d1.doctor_id"));
                doctor.setSpecialization(resultSet.getString("d1.specialization"));

                // Construct appointment map
                appointment = new HashMap<>();
                appointment.put("appointmentId", appointmentId);
                appointment.put("patient", patient);
                appointment.put("doctor", doctor);
                appointment.put("date", date);
                appointment.put("time", time);
                appointment.put("reason", reason);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving appointment with patient and doctor by ID", e);
        }

        return appointment;
    }



}
