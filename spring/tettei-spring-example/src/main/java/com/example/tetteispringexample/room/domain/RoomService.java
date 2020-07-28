package com.example.tetteispringexample.room.domain;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<ReservableRoom> findReservableRooms(LocalDate date);

    MeetingRoom findMeetingRoom(Integer roomId);
}
