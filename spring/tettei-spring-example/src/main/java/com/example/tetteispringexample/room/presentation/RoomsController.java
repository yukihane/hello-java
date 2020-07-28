package com.example.tetteispringexample.room.presentation;

import com.example.tetteispringexample.room.domain.ReservableRoom;
import com.example.tetteispringexample.room.domain.RoomService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    @GetMapping("{date}")
    String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") final LocalDate date,
        final Model model) {
        final List<ReservableRoom> rooms = roomService.findReservableRooms(date);
        model.addAttribute("rooms", rooms);
        return "room/listRooms";
    }

    @GetMapping
    String listRooms(final Model model) {
        final LocalDate today = LocalDate.now();
        model.addAttribute("date", today);
        return listRooms(today, model);
    }
}
