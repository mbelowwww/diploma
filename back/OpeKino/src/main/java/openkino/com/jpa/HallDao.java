package openkino.com.jpa;

import openkino.com.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HallDao extends JpaRepository<Hall, Long> {
    @Query(value = "select h from Hall as h where h.number = :number")
    Hall getNumberHall(@Param("number") Integer number);
}
