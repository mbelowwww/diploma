package openkino.com.jpa;

import openkino.com.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    @Query(value = "select reservation from  Reservation as reservation " +
            "join reservation.kinoUser as kinoUser where " +
            "kinoUser.id = :id")
    List<Reservation> getFindByIdKinoUser(@Param("id") Long id);

    @Query(value = "select reservation from  Reservation as reservation " +
            "join reservation.kinoUser as kinoUser where " +
            "kinoUser.name = :name")
    Reservation getFindByNameKinoUser(@Param("name") String name);

    @Query(value = "select reservation from  Reservation as reservation " +
            "join reservation.kinoUser kinoUser where " +
            "kinoUser.mail = :email")
    Reservation getFindByEmailKinoUser(@Param("email") String email);

    @Query(value = "select count(all r) from Reservation r")
    Integer getCountReservation();
}