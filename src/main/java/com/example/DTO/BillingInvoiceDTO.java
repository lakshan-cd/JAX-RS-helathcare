package com.example.DTO;

import java.util.Date;

public class BillingInvoiceDTO {
    private int patientId;
    private int doctorId;
    private int appointmentId;
    private int invoiceNumber;
    private Date transactionDate;
    private double billedAmount;
    private String paymentStatus;
    private double outstandingBalance;
    private Date issueDate;
    private Date dueDate;
    private double totalAmount;

    public BillingInvoiceDTO() {
    }

    public BillingInvoiceDTO( int patientId, int doctorId, int appointmentId, int invoiceNumber, Date transactionDate, double billedAmount, String paymentStatus, double outstandingBalance, Date issueDate, Date dueDate, double totalAmount) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.invoiceNumber = invoiceNumber;
        this.transactionDate = transactionDate;
        this.billedAmount = billedAmount;
        this.paymentStatus = paymentStatus;
        this.outstandingBalance = outstandingBalance;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(double billedAmount) {
        this.billedAmount = billedAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}