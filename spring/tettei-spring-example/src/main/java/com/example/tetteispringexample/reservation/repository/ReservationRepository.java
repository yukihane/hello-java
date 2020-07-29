package com.example.tetteispringexample.reservation.repository;


import com.example.tetteispringexample.reservation.presentation.Reservation;
import com.example.tetteispringexample.room.domain.ReservableRoomId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);
}
