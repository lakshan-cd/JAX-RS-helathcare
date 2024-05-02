package com.example.dao;

import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.model.PersonEntity;

import javax.persistence.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    public List<PersonEntity> getAllPersons() {
        List<PersonEntity> patients = new ArrayList<>();
        String query = "SELECT * FROM person";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                PersonEntity personEntity = new PersonEntity();
                personEntity.setPersonId(resultSet.getInt("person_id"));
                personEntity.setName(resultSet.getString("name"));
                personEntity.setContactNumber(resultSet.getString("contact_number"));
                personEntity.setAddress(resultSet.getString("address"));
                patients.add(personEntity);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error Occured!");
        }
        return patients;
    }

    public Boolean createPerson(PersonEntity person) {
        String query = "INSERT INTO person (name, contact_number, address) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getContactNumber());
            statement.setString(3, person.getAddress());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new DAOException("Error: No rows were inserted for creating person.");
            }else {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
                throw new DAOException("Error occurred while creating person", e);
        }
    }

    public Boolean updatePerson(Integer id, PersonEntity person) {
        String query = "UPDATE person SET name=?, contact_number=?, address=? WHERE person_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getContactNumber());
            statement.setString(3, person.getAddress());
            statement.setInt(4,id);

            int result = statement.executeUpdate();
            if (result == 0){
                return false;
            }else {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while updating person", e);
        }
    }

    public Boolean deletePerson(int id) {
        String query = "DELETE FROM person WHERE person_id=?";

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

    public PersonEntity getPersonById(int id) {
        String query = "SELECT * FROM person WHERE person_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    PersonEntity person = new PersonEntity();
                    person.setPersonId(resultSet.getInt("person_id"));
                    person.setName(resultSet.getString("name"));
                    person.setContactNumber(resultSet.getString("contact_number"));
                    person.setAddress(resultSet.getString("address"));
                    return person;
                } else {
                    return null; // Person not found with the given ID
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving person by ID", e);
        }
    }

}
