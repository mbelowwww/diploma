package openkino.com.form;

import lombok.Data;
import openkino.com.exceptions.ResponseException;
import openkino.com.jpa.HallDao;
import openkino.com.jpa.PlaceDao;
import openkino.com.jpa.SessionDao;
import openkino.com.models.*;
import openkino.com.service.Helper;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReservationForm {
    private Long id;
    private List<Long> placesId;
    private String personName;
    private Long sessionId;

    public Reservation toReservation(PlaceDao placeDao, SessionDao sessionDao, KinoUser kinoUser) {
        List<Place> places = this.placesId.stream()
                .map(id -> placeDao.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Не найдено место с ID = " + id)))
                .collect(Collectors.toList());
        Session session = sessionDao.findById(this.sessionId).orElseThrow(NullPointerException::new);
        Reservation reservation = new Reservation();
        reservation.setPlaces(places);
        if (kinoUser != null)
            reservation.setKinoUser(kinoUser);
        BigDecimal price = new BigDecimal(0);
        for (int i = 0; i < places.size(); i++) {
            price = price.add(session.getPrice());
        }
        reservation.setStart(LocalDateTime.now());
        reservation.setPrice(price);
        reservation.setStatus(true);
        reservation.setEnd(session.getEnd().minusMinutes(30));
        reservation.setPersonName(this.personName);
        reservation.setSession(session);
        Helper.auditOnCreate(reservation);
        return reservation;
    }
}
