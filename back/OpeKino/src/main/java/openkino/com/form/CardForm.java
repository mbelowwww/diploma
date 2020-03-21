package openkino.com.form;
import lombok.Data;
import openkino.com.models.Card;
import openkino.com.models.KinoUser;

import java.math.BigDecimal;

@Data
public class CardForm {
    private Long number;
    private BigDecimal balance;

    public Card toCard(KinoUser kinoUser){
        Card card = new Card();
        card.setBalance(this.balance);
        card.setNumber(number);
        card.setKinoUser(kinoUser);
        return card;
    }
}
