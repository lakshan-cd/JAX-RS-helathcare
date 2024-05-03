package com.example.dao;

import com.example.DTO.DoctorDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.model.DoctorEntity;
import com.example.model.PersonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorDAO {
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
}
