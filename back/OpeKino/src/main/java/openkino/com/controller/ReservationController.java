package openkino.com.controller;

import openkino.com.form.ReservationForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @PostMapping("")
    public Long saveReservation(@RequestBody ReservationForm reservationForm){
        return null;
    }
}
