package com.devPortes.payments.model;

import com.devPortes.reservations.model.Reservation;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private Long id;
    private PaymentMethod paymentMethod;
    private Reservation reservation;
    private BigDecimal totalAmount;
    private LocalDateTime datePayment;
    private String referenceTransaction;
    private String notes;
    private PaymentStateEnum stateEnum;


    public Payment() {
    }

    public Payment(Long id, PaymentMethod paymentMethod, Reservation reservation, BigDecimal totalAmount, LocalDateTime datePayment, String referenceTransaction, String notes, PaymentStateEnum stateEnum) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.reservation = reservation;
        this.totalAmount = totalAmount;
        this.datePayment = datePayment;
        this.referenceTransaction = referenceTransaction;
        this.notes = notes;
        this.stateEnum = stateEnum;
    }

    public Long getId() {
        return id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getDatePayment() {
        return datePayment;
    }

    public String getReferenceTransaction() {
        return referenceTransaction;
    }

    public String getNotes() {
        return notes;
    }

    public PaymentStateEnum getStateEnum() {
        return stateEnum;
    }
}