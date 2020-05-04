package openkino.com.jpa;

import openkino.com.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CardDao extends JpaRepository<Card, Long> {
    List<Card> findAllByKinoUser_Id(Long id);
}
