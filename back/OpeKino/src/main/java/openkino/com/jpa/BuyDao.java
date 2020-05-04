package openkino.com.jpa;

import openkino.com.models.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyDao extends JpaRepository<Buy, Long> {

    @Query("select b from Buy b where b.card.kinoUser.id = ?1")
    List<Buy> findAllByKinoUser_Id(Long KinoUserId);
}
