package openkino.com.service;

import openkino.com.VO.ReservationVO;
import openkino.com.form.ReservationForm;
import openkino.com.models.KinoUser;
import openkino.com.models.Place;
import openkino.com.models.Reservation;

import java.util.List;

public interface ReservationService {
    Long saveReservation(ReservationForm reservationForm, KinoUser kinoUser);
    List<Reservation> findAllReservationByIdKinoUser(Long idKinoUser);
    Reservation findReservationById(Long idReservation);
    List<ReservationVO> findAllBySessionId(Long sessionId);
    List<Place> findAllPlaceByIdSessionAndReservationTue(Long sessionId);
}
