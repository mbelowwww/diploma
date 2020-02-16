package openkino.com.jpa;

import openkino.com.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceDao extends JpaRepository<Place, Long> {
    List<Place> findAllByHall_Id(Long id);
}
