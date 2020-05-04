package openkino.com.form;
import lombok.Data;
import openkino.com.exceptions.ResponseException;
import openkino.com.models.Card;
import openkino.com.models.KinoUser;

import java.math.BigDecimal;

@Data
public class CardForm {
    private Long number;
    private BigDecimal balance;

    public Card toCard(KinoUser kinoUser){
        ResponseException.nullHandler(kinoUser,"Пользователь не найден!");
        Card card = new Card();
        card.setBalance(this.balance);
        card.setId(number);
        card.setKinoUser(kinoUser);
        return card;
    }
}
