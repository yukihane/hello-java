package com.example.tetteispringexample.room.repository;

import com.example.tetteispringexample.room.domain.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
}
