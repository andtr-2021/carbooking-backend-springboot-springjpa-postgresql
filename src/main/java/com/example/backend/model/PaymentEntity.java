package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="TBL_PAYMENTS")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PaymentEntity() {

    }

    public PaymentEntity(String paymentType, String paymentAmount, String paymentStatus, String paymentDate) {
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    @Column(name="payment_type")
    private String paymentType;

    @Column(name="payment_amount")
    private String paymentAmount;

    @Column(name="payment_status")
    private String paymentStatus;

    @Column(name="payment_date")
    private String paymentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
