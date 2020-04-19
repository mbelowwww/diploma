package openkino.com.form;

import lombok.Data;
import openkino.com.exceptions.ResponseException;
import openkino.com.jpa.CardDao;
import openkino.com.jpa.DiscountDao;
import openkino.com.jpa.ReservationDao;
import openkino.com.models.Buy;
import openkino.com.models.Card;
import openkino.com.models.Reservation;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class BuyForm {
    private Long id;
    private Long cardId;
    private Long discountId;
    private Long reservationId;

    public Buy toBuy(CardDao cardDao, DiscountDao discountDao, ReservationDao reservationDao) {
        Buy buy = new Buy();
        Card card = cardDao.findById(cardId).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Карта не найдена."));
        Reservation reservation = reservationDao.findById(reservationId).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Бронь не найдена."));
        buy.setDiscount(discountDao.findById(discountId).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Скидка не найдена.")));
        buy.setCard(card);
        buy.setReservation(reservation);
        buy.setPrice(reservation.getPrice());
        card.setBalance(card.getBalance().min(reservation.getPrice()));
        if (card.getBalance().longValue() < 0)
            throw new ResponseException(HttpStatus.CONFLICT, "Не достаточно середств для покупки");
        cardDao.save(card);
        buy.setDateTime(LocalDateTime.now());
        return buy;
    }
}