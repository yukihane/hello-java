package com.example.tetteispringexample.reservation.domain;

import com.example.tetteispringexample.reservation.presentation.Reservation;
import com.example.tetteispringexample.reservation.repository.ReservationRepository;
import com.example.tetteispringexample.room.domain.AlreadyReservedException;
import com.example.tetteispringexample.room.domain.ReservableRoom;
import com.example.tetteispringexample.room.domain.ReservableRoomId;
import com.example.tetteispringexample.room.domain.UnavailableReservationException;
import com.example.tetteispringexample.room.repository.ReservableRoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservableRoomRepository reservableRoomRepository;

    @Override
    public List<Reservation> findReservations(final ReservableRoomId reservableRoomId) {
        return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
    }

    @Override
    public Reservation reserve(final Reservation reservation) {
        final ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
        // 悲観ロック
        final ReservableRoom reservable = reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId);
        if (reservable == null) {
            throw new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。");
        }
        // 重複チェック
        final boolean overlap = reservationRepository
            .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId).stream()
            .anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
        }
        // 予約情報の登録
        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
    public void cancel(@P("reservation") final Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation findOne(final Integer reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }
}