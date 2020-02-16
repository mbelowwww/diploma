package openkino.com.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import openkino.com.models.Buy;
import openkino.com.models.KinoUser;
import openkino.com.models.Place;
import openkino.com.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservationForm {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime end;
    private Reservation.ReservationStatus status;
    private BigDecimal price;
    private KinoUser kinoUser;
    private Place place;
    private Buy buy;

    public Reservation toReservation(ReservationForm reservationForm) {
        Reservation reservation = new Reservation();
        reservation.setBuy(reservation.getBuy());
        return reservation;
    }
}
