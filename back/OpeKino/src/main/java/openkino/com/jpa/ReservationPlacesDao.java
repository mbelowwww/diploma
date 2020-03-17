package openkino.com.jpa;

import openkino.com.models.ReservationPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationPlacesDao extends JpaRepository<ReservationPlaces, Long> {
}
