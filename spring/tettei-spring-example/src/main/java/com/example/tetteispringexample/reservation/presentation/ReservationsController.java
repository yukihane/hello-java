package com.example.tetteispringexample.reservation.presentation;

import com.example.tetteispringexample.reservation.domain.ReservationService;
import com.example.tetteispringexample.room.domain.AlreadyReservedException;
import com.example.tetteispringexample.room.domain.ReservableRoom;
import com.example.tetteispringexample.room.domain.ReservableRoomId;
import com.example.tetteispringexample.room.domain.RoomService;
import com.example.tetteispringexample.room.domain.UnavailableReservationException;
import com.example.tetteispringexample.security.UserAuthentication.Principal;
import com.example.tetteispringexample.user.domain.RoleName;
import com.example.tetteispringexample.user.domain.User;
import com.example.tetteispringexample.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("reservations/{date}/{roomId}")
@RequiredArgsConstructor
public class ReservationsController {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final UserRepository userRepository;

    @ModelAttribute
    ReservationForm setUpForm() {
        final ReservationForm form = new ReservationForm();
        // デフォルト値
        form.setStartTime(LocalTime.of(9, 0));
        form.setEndTime(LocalTime.of(10, 0));
        return form;
    }

    @RequestMapping(method = RequestMethod.GET)
    String reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") final LocalDate date,
        @PathVariable("roomId") final Integer roomId, final Model model) {
        final ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, date);
        final List<Reservation> reservations = reservationService.findReservations(reservableRoomId);
        final List<LocalTime> timeList = Stream.iterate(LocalTime.of(0, 0), t -> t.plusMinutes(30)).limit(24 * 2)
            .collect(Collectors.toList());

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(userId).get();

        model.addAttribute("room", roomService.findMeetingRoom(roomId));
        model.addAttribute("reservations", reservations);
        model.addAttribute("timeList", timeList);
        model.addAttribute("user", user);
        return "reservation/reservationForm";
    }

    private User dummyUser() {
        final User user = new User();
        user.setUserId("taro-yamada");
        user.setFirstName("太郎");
        user.setLastName("山田");
        user.setRoleName(RoleName.USER);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    String reserve(@Validated final ReservationForm form, final BindingResult bindingResult,
        @AuthenticationPrincipal final Principal userDetails,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") final LocalDate date,
        @PathVariable("roomId") final Integer roomId, final Model model) {
        if (bindingResult.hasErrors()) {
            return reserveForm(date, roomId, model);
        }
        final ReservableRoom reservableRoom = new ReservableRoom(new ReservableRoomId(roomId, date));
        final Reservation reservation = new Reservation();
        reservation.setStartTime(form.getStartTime());
        reservation.setEndTime(form.getEndTime());
        reservation.setReservableRoom(reservableRoom);
        final User user = userRepository.findById(userDetails.getName()).get();
        reservation.setUser(user);
        try {
            reservationService.reserve(reservation);
        } catch (UnavailableReservationException | AlreadyReservedException e) {
            model.addAttribute("error", e.getMessage());
            return reserveForm(date, roomId, model);
        }
        return "redirect:/reservations/{date}/{roomId}";
    }

    @RequestMapping(method = RequestMethod.POST, params = "cancel")
    String cancel(@RequestParam("reservationId") final Integer reservationId,
        @PathVariable("roomId") final Integer roomId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") final LocalDate date, final Model model) {
        try {
            final Reservation reservation = reservationService.findOne(reservationId);
            reservationService.cancel(reservation);
        } catch (final AccessDeniedException e) {
            model.addAttribute("error", e.getMessage());
            return reserveForm(date, roomId, model);
        }
        return "redirect:/reservations/{date}/{roomId}";
    }
}