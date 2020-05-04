package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.exceptions.ResponseException;
import openkino.com.form.CardForm;
import openkino.com.jpa.CardDao;
import openkino.com.jpa.KinoUserDao;
import openkino.com.models.Card;
import openkino.com.models.KinoUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private CardDao cardDao;
    private KinoUserDao kinoUserDao;


    @Override
    public Long saveCard(CardForm card, KinoUser kinoUser) {
        return cardDao.save(card.toCard(kinoUser)).getId();
    }

    @Override
    public void deleteCardById(Long id) {
        Card card = findCard(id);
        cardDao.delete(card);
    }

    @Override
    public List<Card> findAllCardsByIdFilmUser(KinoUser kinoUser) {
        return cardDao.findAllByKinoUser_Id(kinoUser.getId());
    }

    @Override
    public Card findCardById(Long id) {
        return findCard(id);
    }

    @Override
    public Long addBalance(Long id, BigDecimal money) {
        Card card = findCard(id);
        int result = money.compareTo(BigDecimal.valueOf(0));
        if (result >= 0) {
            card.setBalance(card.getBalance().add(money));
        }
        cardDao.save(card);
        return card.getId();
    }

    public Card findCard(Long id) {
        return cardDao.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Не найдена карта с id = " + id));
    }
}
