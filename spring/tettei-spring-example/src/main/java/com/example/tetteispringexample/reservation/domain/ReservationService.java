package com.example.tetteispringexample.reservation.domain;

import com.example.tetteispringexample.reservation.presentation.Reservation;
import com.example.tetteispringexample.room.domain.ReservableRoomId;
import java.util.List;

public interface ReservationService {

    List<Reservation> findReservations(ReservableRoomId reservableRoomId);

    Reservation reserve(Reservation reservation);

    void cancel(Reservation reservation);

    Reservation findOne(Integer reservationId);

}
