package openkino.com.jpa;

import openkino.com.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByKinoUser_Id(Long id);

    @Query(value = "SELECT R.*\n" +
            "FROM RESERVATION R\n" +
            "WHERE R.SESSION_ID = ?1 AND R.STATUS = 'TRUE'",nativeQuery = true)
    List<Reservation> findAllByStatusTrueAndSession_Id(Long sessionId);
}