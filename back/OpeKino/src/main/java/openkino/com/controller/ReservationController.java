package openkino.com.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import openkino.com.VO.ReservationVO;
import openkino.com.form.ReservationForm;
import openkino.com.models.Place;
import openkino.com.models.Reservation;
import openkino.com.service.ReservationService;
import openkino.com.view.Views;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    public Long saveReservation(@RequestBody ReservationForm reservationForm) {// TODO: 13.03.2020 ВЫТЯГИВАТЬ ПОЛЬЗОВАТЕЛЯ ПО АВТОРИЗАЦИИ
        return reservationService.saveReservation(reservationForm, null);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/session")
    public List<ReservationVO> findAllReservationByIdSession(@RequestParam("sessionId") Long sessionId) {
        return reservationService.findAllBySessionId(sessionId);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/session/place")
    public List<Place> findAllPlaceByIdSessionAndReservationTue(@RequestParam("sessionId") Long sessionId) {
        return reservationService.findAllPlaceByIdSessionAndReservationTue(sessionId);
    }
}
