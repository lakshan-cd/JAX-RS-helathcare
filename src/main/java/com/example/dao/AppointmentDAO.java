package com.example.dao;

import com.example.DTO.AppoinmentDTO;
import com.example.DTO.DoctorDTO;
import com.example.DTO.PrescriptionDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


}
