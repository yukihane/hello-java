package com.example.tetteispringexample.room.domain;

import com.example.tetteispringexample.room.repository.MeetingRoomRepository;
import com.example.tetteispringexample.room.repository.ReservableRoomRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final ReservableRoomRepository reservableRoomRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    @Override
    public List<ReservableRoom> findReservableRooms(final LocalDate date) {
        return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
    }

    @Override
    public MeetingRoom findMeetingRoom(final Integer roomId) {
        return meetingRoomRepository.findById(roomId).orElse(null);
    }
}