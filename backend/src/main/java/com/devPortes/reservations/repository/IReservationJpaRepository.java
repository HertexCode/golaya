package com.devPortes.reservations.repository;

import com.devPortes.reservations.entities.ReservationEntity;
import com.devPortes.reservations.model.EstadoReservationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByReservationDateAndStateNot(LocalDate date, EstadoReservationEnum state);
    List<ReservationEntity> findByReservationDateBetweenAndStateNot(LocalDate startDate, LocalDate endDate, EstadoReservationEnum state);
    List<ReservationEntity> findByFieldEntity_IdAndReservationDateAndStateNot(Long fieldId, LocalDate date, EstadoReservationEnum state);
    List<ReservationEntity> findByFieldEntity_IdAndReservationDateBetweenAndStateNot(Long fieldId, LocalDate startDate, LocalDate endDate, EstadoReservationEnum state);
    boolean existsByFieldEntity_IdAndReservationDateAndStateNotAndStartTimeLessThanAndEndTimeGreaterThan(
            Long fieldId, LocalDate date, EstadoReservationEnum state, java.time.LocalTime endTime, java.time.LocalTime startTime);
    List<ReservationEntity> findByClientEntity_IdAndState(Long userId, EstadoReservationEnum state);
    List<ReservationEntity> findByClientEntity_IdAndStateIn(Long userId, List<EstadoReservationEnum> states);
    List<ReservationEntity> findByClientEntity_IdAndStateInOrderByReservationDateDesc(
            Long userId, List<EstadoReservationEnum> states);
    List<ReservationEntity> findByClientEntity_IdOrderByReservationDateDesc(Long userId);
    List<ReservationEntity> findAllByOrderByReservationDateDescStartTimeAsc();
}
