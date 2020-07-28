package com.example.tetteispringexample.room.domain;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Override
    public List<ReservableRoom> findReservableRooms(final LocalDate date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MeetingRoom findMeetingRoom(final Integer roomId) {
        // TODO Auto-generated method stub
        return null;
    }

}
