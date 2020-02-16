package openkino.com.jpa;

import openkino.com.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionDao extends JpaRepository<Position, Long> {
}
