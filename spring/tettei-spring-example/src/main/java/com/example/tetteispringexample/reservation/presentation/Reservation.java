package com.example.tetteispringexample.reservation.presentation;

import com.example.tetteispringexample.room.domain.ReservableRoom;
import com.example.tetteispringexample.user.domain.User;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "reserved_date"), @JoinColumn(name = "room_id") })
    private ReservableRoom reservableRoom;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public boolean overlap(final Reservation target) {
        if (!Objects.equals(reservableRoom.getReservableRoomId(), target.reservableRoom.getReservableRoomId())) {
            return false;
        }
        if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
            return true;
        }
        return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
    }
}
