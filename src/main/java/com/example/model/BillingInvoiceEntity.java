package com.example.model;

import javax.persistence.*;
import java.util.Date;
@Entity
public class BillingInvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "FK_patient_id"))
    private PersonEntity patientId;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "FK_doctor_id"))
    private DoctorEntity doctorId;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id", foreignKey = @ForeignKey(name = "FK_appointment_id"))
    private AppointmentEntity appointmentId;

    @Column(name = "invoice_number")
    private int invoiceNumber;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "billed_amount")
    private double billedAmount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "outstanding_balance")
    private double outstandingBalance;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "total_amount")
    private double totalAmount;

    public BillingInvoiceEntity() {
    }

    public BillingInvoiceEntity(int transactionId, PersonEntity patientId, DoctorEntity doctorId, AppointmentEntity appointmentId, int invoiceNumber, Date transactionDate, double billedAmount, String paymentStatus, double outstandingBalance, Date issueDate, Date dueDate, double totalAmount) {
        this.transactionId = transactionId;
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

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public PersonEntity getPatientId() {
        return patientId;
    }

    public void setPatientId(PersonEntity patientId) {
        this.patientId = patientId;
    }

    public DoctorEntity getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorEntity doctorId) {
        this.doctorId = doctorId;
    }

    public AppointmentEntity getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(AppointmentEntity appointmentId) {
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
