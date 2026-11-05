package com.devPortes.reservations.service;

import com.devPortes.fields.exceptions.FieldRepositoryNotFoundException;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import com.devPortes.reservations.dto.NewReservationRequestDto;
import com.devPortes.reservations.dto.NewReservationResponseDto;
import com.devPortes.reservations.mapper.ReservationInMapper;
import com.devPortes.reservations.model.Reservation;
import com.devPortes.reservations.repository.ReservationJpaRepositoryAdapter;
import com.devPortes.reservations.exceptions.ReservationScheduleConflictException;
import com.devPortes.reservations.exceptions.InvalidReservationScheduleException;
import com.devPortes.users.exceptions.UserNotFoundException;
import com.devPortes.users.model.Client;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class NewReservationUseCase implements INewReservationUseCase {
    private final ReservationJpaRepositoryAdapter repository;
    private final UserJpaRepositoryAdapter clientRepository;
    private final FieldJpaRepositoryAdapter fieldRepository;

    @Override
    public NewReservationResponseDto execute(NewReservationRequestDto dto) {

        LocalDateTime ahora = LocalDateTime.now(ZoneId.of("America/Bogota"));
        LocalDateTime inicioReserva = LocalDateTime.of(dto.reservationDate(), dto.startTime());
        if (!inicioReserva.isAfter(ahora)) {
            throw new InvalidReservationScheduleException();
        }

        //verificar que el cliente asociado exista
        Client client = clientRepository.finById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(dto.userId())));
        //verificar que la cancha exista
        Field field = fieldRepository.findById(dto.fieldId())
                .orElseThrow(() -> new FieldRepositoryNotFoundException(dto.fieldId()));

        if (repository.existsOverlapping(dto.fieldId(), dto.reservationDate(), dto.startTime(), dto.endTime())) {
            throw new ReservationScheduleConflictException();
        }

        Reservation reservation = ReservationInMapper.toModel(client, field, dto);

        Reservation saveReservation = repository.save(reservation);
        return ReservationInMapper.toReservationResponse(saveReservation);
    }
}
