package com.example.dao;

import com.example.DTO.AppoinemntResponseDTO;
import com.example.DTO.AppoinmentDTO;
import com.example.DTO.BillingInvoiceDTO;
import com.example.DTO.DoctorResponseDTO;
import com.example.DTO.MedicalRecordDTO;
import com.example.application.DatabaseConnection;
import com.example.exception.DAOException;
import com.example.model.AppointmentEntity;
import com.example.model.DoctorEntity;
import com.example.model.PersonEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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


    public List<Map<String, Object>> getAllBillingInvoicesWithPatientsAndDoctors() {
        List<Map<String, Object>> billingInvoices = new ArrayList<>();
        String query = "SELECT bi.*, p.*, d.*, pp.name AS patient_name, pp.contact_number AS patient_contact,pp.address AS patient_address, pd.name AS doctor_name, pd.contact_number AS doctor_contact, pd.address AS doctor_address " +
                "FROM billinginvoice bi " +
                "LEFT JOIN patient p ON bi.patient_id = p.patient_id " +
                "LEFT JOIN person pp ON p.person_id = pp.person_id " +
                "LEFT JOIN doctor d ON bi.doctor_id = d.doctor_id " +
                "LEFT JOIN person pd ON d.person_id = pd.person_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transaction_id");
                int invoiceNumber = resultSet.getInt("invoice_number");
                Date transactionDate = resultSet.getDate("transaction_date");
                double billedAmount = resultSet.getDouble("billed_amount");
                String paymentStatus = resultSet.getString("payment_status");
                double outstandingBalance = resultSet.getDouble("outstanding_balance");
                Date issueDate = resultSet.getDate("issue_date");
                Date dueDate = resultSet.getDate("due_date");
                double totalAmount = resultSet.getDouble("total_amount");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setName(resultSet.getString("patient_name"));
                patient.setContactNumber(resultSet.getString("patient_contact"));
                patient.setAddress(resultSet.getString("patient_address"));
                patient.setPersonId(resultSet.getInt("person_id"));
                // Set other patient properties as needed

                // Construct doctor entity
                DoctorResponseDTO doctor = new DoctorResponseDTO();
                doctor.setName(resultSet.getString("doctor_name"));
                doctor.setContactNumber(resultSet.getString("doctor_contact"));
                doctor.setAddress(resultSet.getString("doctor_address"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                // Set other doctor properties as needed

                // Construct billing invoice map
                Map<String, Object> billingInvoice = new HashMap<>();
                billingInvoice.put("transactionId", transactionId);
                billingInvoice.put("invoiceNumber", invoiceNumber);
                billingInvoice.put("transactionDate", transactionDate);
                billingInvoice.put("billedAmount", billedAmount);
                billingInvoice.put("paymentStatus", paymentStatus);
                billingInvoice.put("outstandingBalance", outstandingBalance);
                billingInvoice.put("issueDate", issueDate);
                billingInvoice.put("dueDate", dueDate);
                billingInvoice.put("totalAmount", totalAmount);
                billingInvoice.put("patient", patient);
                billingInvoice.put("doctor", doctor);

                billingInvoices.add(billingInvoice);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving billing invoices with patients and doctors", e);
        }

        return billingInvoices;
    }

    public Map<String, Object> getBillingInvoiceByIdWithPatientAndDoctor(int id) {
        Map<String, Object> billingInvoice = null;
        String query = "SELECT bi.*, p.*, d.*, pp.name AS patient_name, pp.contact_number AS patient_contact, pp.address AS patient_address, pd.name AS doctor_name, pd.contact_number AS doctor_contact, pd.address AS doctor_address " +
                "FROM billinginvoice bi " +
                "LEFT JOIN patient p ON bi.patient_id = p.patient_id " +
                "LEFT JOIN person pp ON p.person_id = pp.person_id " +
                "LEFT JOIN doctor d ON bi.doctor_id = d.doctor_id " +
                "LEFT JOIN person pd ON d.person_id = pd.person_id " +
                "WHERE bi.transaction_id = ?";

                try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int transactionId = resultSet.getInt("transaction_id");
                int invoiceNumber = resultSet.getInt("invoice_number");
                Date transactionDate = resultSet.getDate("transaction_date");
                double billedAmount = resultSet.getDouble("billed_amount");
                String paymentStatus = resultSet.getString("payment_status");
                double outstandingBalance = resultSet.getDouble("outstanding_balance");
                Date issueDate = resultSet.getDate("issue_date");
                Date dueDate = resultSet.getDate("due_date");
                double totalAmount = resultSet.getDouble("total_amount");

                // Construct patient entity
                PersonEntity patient = new PersonEntity();
                patient.setName(resultSet.getString("patient_name"));
                patient.setContactNumber(resultSet.getString("patient_contact"));
                patient.setAddress(resultSet.getString("patient_address"));
                // Set other patient properties as needed

                // Construct doctor entity
                DoctorResponseDTO doctor = new DoctorResponseDTO();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("doctor_name"));
                doctor.setContactNumber(resultSet.getString("doctor_contact"));
                doctor.setAddress(resultSet.getString("doctor_address"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                // Set other doctor properties as needed

                // Construct billing invoice map
                billingInvoice = new HashMap<>();
                billingInvoice.put("transactionId", transactionId);
                billingInvoice.put("invoiceNumber", invoiceNumber);
                billingInvoice.put("transactionDate", transactionDate);
                billingInvoice.put("billedAmount", billedAmount);
                billingInvoice.put("paymentStatus", paymentStatus);
                billingInvoice.put("outstandingBalance", outstandingBalance);
                billingInvoice.put("issueDate", issueDate);
                billingInvoice.put("dueDate", dueDate);
                billingInvoice.put("totalAmount", totalAmount);
                billingInvoice.put("patient", patient);
                billingInvoice.put("doctor", doctor);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Error occurred while retrieving billing invoice by ID", e);
        }

        return billingInvoice;
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
