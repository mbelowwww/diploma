package openkino.com.jpa;

import openkino.com.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CardDao extends JpaRepository<Card, Long> {
    List<Card> findAllByKinoUser_Id(Long id);
}
