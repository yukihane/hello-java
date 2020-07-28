package com.example.tetteispringexample.room.presentation;


import com.example.tetteispringexample.room.domain.ReservableRoom;
import com.example.tetteispringexample.room.domain.RoomService;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("rooms")
public class RoomsController {
    @Autowired
    RoomService roomService;

    @RequestMapping(value = "{date}", method = RequestMethod.GET)
    String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") final LocalDate date, final Model model) {
        final List<ReservableRoom> rooms = roomService.findReservableRooms(date);
        model.addAttribute("rooms", rooms);
        return "room/listRooms";
    }

    @RequestMapping(method = RequestMethod.GET)
    String listRooms(final Model model) {
        final LocalDate today = LocalDate.now();
        model.addAttribute("date", today);
        return listRooms(today, model);
    }
}
