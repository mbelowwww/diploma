package openkino.com.jpa;

import openkino.com.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceDao extends JpaRepository<Place, Long> {
    List<Place> findAllByHall_Id(Long id);

    @Query(value = "SELECT P.*\n" +
            "FROM PLACE P\n" +
            "         INNER JOIN RESERVATIONPLACES RP on P.ID = RP.PLACE_ID\n" +
            "         INNER JOIN RESERVATION R ON RP.RESERVATION_ID = R.ID\n" +
            "WHERE P.STATUS = 'TRUE'\n" +
            "  AND R.SESSION_ID = ?1", nativeQuery = true)
    List<Place> findAllByReservationAndSessionId(Long sessionId);
}
