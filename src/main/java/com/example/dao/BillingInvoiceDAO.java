package com.example.dao;

import com.example.DTO.AppoinmentDTO;
import com.example.DTO.BillingInvoiceDTO;
import com.example.DTO.MedicalRecordDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillingInvoiceDAO {


    public Boolean createInvoice(BillingInvoiceDTO request) {

        String query = "INSERT INTO billinginvoice (patient_id, doctor_id, appointment_id, invoice_number, transaction_date, billed_amount, payment_status, outstanding_balance, issue_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setInt(2, request.getDoctorId());
            statement.setInt(3, request.getAppointmentId());
            statement.setInt(4, request.getInvoiceNumber());
            statement.setDate(5, new java.sql.Date(request.getTransactionDate().getTime()));
            statement.setDouble(6, request.getBilledAmount());
            statement.setString(7, request.getPaymentStatus());
            statement.setDouble(8, request.getOutstandingBalance());
            statement.setDate(9, new java.sql.Date(request.getIssueDate().getTime()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while creating doctor", e);
        }
    }

    public Boolean updateInvoice(Integer id, BillingInvoiceDTO request) {
        String query = "UPDATE billinginvoice SET patient_id=?, doctor_id=?, appointment_id=?, invoice_number=?, transaction_date=?, billed_amount=?, payment_status=?, outstanding_balance=?, issue_date=? WHERE transaction_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, request.getPatientId());
            statement.setInt(2, request.getDoctorId());
            statement.setInt(3, request.getAppointmentId());
            statement.setInt(4, request.getInvoiceNumber());
            statement.setDate(5, new java.sql.Date(request.getTransactionDate().getTime()));
            statement.setDouble(6, request.getBilledAmount());
            statement.setString(7, request.getPaymentStatus());
            statement.setDouble(8, request.getOutstandingBalance());
            statement.setDate(9, new java.sql.Date(request.getIssueDate().getTime()));
            statement.setInt(10, id); // Updated index for transaction_id

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated != 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while updating invoice", e);
        }
    }

    public Boolean deleteInvoice(int id) {
        String query = "DELETE FROM billinginvoice WHERE transaction_id=?";

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
