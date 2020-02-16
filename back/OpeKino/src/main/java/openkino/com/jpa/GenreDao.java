package openkino.com.jpa;

import openkino.com.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre, Long> {
}
