package com.devPortes.reservations.model;

import com.devPortes.fields.model.Field;
import com.devPortes.payments.model.Payment;
import com.devPortes.users.model.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reservation {


    private Long id;
    private Client user;
    private Field field;
    private List<Payment> payments;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int totalHours;
    private BigDecimal totalPay;
    private BigDecimal remainingPayment;
    private EstadoReservationEnum state;

    public Reservation() {
    }

    public Reservation(Long id, Client user, Field field, List<Payment> payments, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, int totalHours, BigDecimal totalPay, BigDecimal remainingPayment, EstadoReservationEnum state) {
        this.id = id;
        this.user = user;
        this.field = field;
        this.payments = payments;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalHours = totalHours;
        this.totalPay = totalPay;
        this.remainingPayment = remainingPayment;
        this.state = state;
    }

    public static Reservation create(Client user, Field field, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, int totalHours, BigDecimal totalPay, BigDecimal remainingPayment) {
        EstadoReservationEnum state = EstadoReservationEnum.PENDIENTE;
        return new Reservation(null, user, field, null, reservationDate, startTime, endTime, totalHours, totalPay, remainingPayment, state);
    }

    public static Reservation reconstitute(Long id, Client user, Field field, List<Payment> payments, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, int totalHours, BigDecimal totalPay, BigDecimal remainingPayment, EstadoReservationEnum state) {
        return new Reservation(id, user, field, payments, reservationDate, startTime, endTime, totalHours, totalPay, remainingPayment, state);
    }

    public Long getId() {
        return id;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public Client getUser() {
        return user;
    }

    public Field getField() {
        return field;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public BigDecimal getRemainingPayment() {
        return remainingPayment;
    }

    public EstadoReservationEnum getState() {
        return state;
    }
}