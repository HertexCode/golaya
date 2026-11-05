package com.devPortes.reservations.mapper;

import com.devPortes.fields.entities.FieldEntity;
import com.devPortes.fields.mapper.FieldOutMapper;
import com.devPortes.fields.model.Field;
import com.devPortes.payments.mapper.PaymentOutMapper;
import com.devPortes.payments.model.Payment;
import com.devPortes.reservations.entities.ReservationEntity;
import com.devPortes.reservations.model.EstadoReservationEnum;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.users.entities.ClientEntity;
import com.devPortes.users.mapper.UserOutMapper;
import com.devPortes.users.model.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationOutMapper {

    public static ReservationEntity toEntity(Reservation model, ClientEntity client, FieldEntity field) {
        ReservationEntity entity = new ReservationEntity();
        entity.setClientEntity(client);
        entity.setFieldEntity(field);
        entity.setReservationDate(model.getReservationDate());
        entity.setStartTime(model.getStartTime());
        entity.setEndTime(model.getEndTime());
        entity.setTotalHours(model.getTotalHours());
        entity.setTotalPay(model.getTotalPay());
        entity.setRemainingPayment(model.getRemainingPayment());
        entity.setState(model.getState());
        return entity;
    }

    public static Reservation toModel(ReservationEntity reservation) {
        return Reservation.reconstitute(
                reservation.getId(),
                UserOutMapper.toClientCompleteModel(reservation.getClientEntity()),
                FieldOutMapper.toModel(reservation.getFieldEntity()),
                reservation.getPayments().stream()
                        .map(PaymentOutMapper::toModel)
                        .toList(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getTotalHours(),
                reservation.getTotalPay(),
                reservation.getRemainingPayment(),
                reservation.getState()
        );
    }

    public static List<Reservation> toModelList(List<ReservationEntity> reservations) {
        return reservations.stream().map(ReservationOutMapper::toModel)
                .collect(Collectors.toList());

    }

}
