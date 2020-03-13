package openkino.com.jpa;

import openkino.com.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByKinoUser_Id(Long id);

    List<Reservation> findAllByStatusFalseOrStatusIsNullAndSession_Id(Long sessionId);


}