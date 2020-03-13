package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.form.ReservationForm;
import openkino.com.jpa.PlaceDao;
import openkino.com.jpa.ReservationDao;
import openkino.com.jpa.SessionDao;
import openkino.com.models.KinoUser;
import openkino.com.models.Place;
import openkino.com.models.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final PlaceDao placeDao;
    private final SessionDao sessionDao;

    @Override
    public Long saveReservation(ReservationForm reservationForm, KinoUser kinoUser) {
            Reservation reservation = reservationForm.toReservation(placeDao,sessionDao,kinoUser);
            return reservationDao.save(reservation).getId();
    }

    @Override
    public List<Reservation> findAllReservationByIdKinoUser(Long idKinoUser) {
        return null;
    }

    @Override
    public Reservation findReservationById(Long idReservation) {
        return null;
    }

    @Override
    public List<Reservation> findAllBySessionId(Long sessionId) {
        return null;
    }
}
