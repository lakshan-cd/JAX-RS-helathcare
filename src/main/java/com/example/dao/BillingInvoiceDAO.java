package com.example.dao;

import com.example.DTO.AppoinmentDTO;
import com.example.DTO.BillingInvoiceDTO;
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

}
