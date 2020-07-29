package com.example.tetteispringexample;

import com.example.tetteispringexample.room.domain.MeetingRoom;
import com.example.tetteispringexample.room.domain.ReservableRoom;
import com.example.tetteispringexample.room.domain.ReservableRoomId;
import com.example.tetteispringexample.room.repository.MeetingRoomRepository;
import com.example.tetteispringexample.room.repository.ReservableRoomRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@RequiredArgsConstructor
public class TetteiSpringExampleApplication implements CommandLineRunner {

    private final ReservableRoomRepository reservableRoomRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    public static void main(final String[] args) {
        SpringApplication.run(TetteiSpringExampleApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final List<MeetingRoom> rooms = meetingRoomRepository.findAll();
        final LocalDate currentDate = LocalDate.now();
        for (final MeetingRoom room : rooms) {
            for (int i = 0; i < 77; i++) {
                final LocalDate date = currentDate.plusDays(i - 7);
                final ReservableRoom rr = new ReservableRoom();
                final ReservableRoomId reservableRoomId = new ReservableRoomId(room.getRoomId(), date);
                rr.setReservableRoomId(reservableRoomId);
                rr.setMeetingRoom(room);

                reservableRoomRepository.save(rr);
            }
        }
    }
}
