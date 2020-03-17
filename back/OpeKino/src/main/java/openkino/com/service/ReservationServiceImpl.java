package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.VO.ReservationVO;
import openkino.com.form.ReservationForm;
import openkino.com.jpa.PlaceDao;
import openkino.com.jpa.ReservationDao;
import openkino.com.jpa.ReservationPlacesDao;
import openkino.com.jpa.SessionDao;
import openkino.com.models.KinoUser;
import openkino.com.models.Place;
import openkino.com.models.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final PlaceDao placeDao;
    private final SessionDao sessionDao;
    private final ReservationPlacesDao reservationPlacesDao;

    @Override
    public Long saveReservation(ReservationForm reservationForm, KinoUser kinoUser) {
        Reservation reservation = reservationForm.toReservation(placeDao, sessionDao, reservationDao, reservationPlacesDao, kinoUser);
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
    public List<ReservationVO> findAllBySessionId(Long sessionId) {
        List<ReservationVO> reservationVOS = reservationDao.findAllByStatusTrueAndSession_Id(sessionId)
                .stream()
                .map(ReservationVO::new)
                .collect(Collectors.toList());
        return reservationVOS;
    }

    @Override
    public List<Place> findAllPlaceByIdSessionAndReservationTue(Long sessionId) {
        return placeDao.findAllByReservationAndSessionId(sessionId);
    }
}
