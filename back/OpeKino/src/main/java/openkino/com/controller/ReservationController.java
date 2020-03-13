package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.form.ReservationForm;
import openkino.com.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    public Long saveReservation(@RequestBody ReservationForm reservationForm){// TODO: 13.03.2020 ВЫТЯГИВАТЬ ПОЛЬЗОВАТЕЛЯ ПО АВТОРИЗАЦИИ
        return reservationService.saveReservation(reservationForm,null);
    }
}
