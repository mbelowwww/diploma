package openkino.com.VO;

import lombok.Data;
import openkino.com.models.Buy;
import openkino.com.models.Card;
import openkino.com.models.Discount;
import openkino.com.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BuyVO {
    private Long id;
    private LocalDateTime dateTime;
    private BigDecimal price;
    private Card card;
    private Discount discount;
    private ReservationVO reservation;

    public BuyVO(Buy buy){
        this.id = buy.getId();
        this.dateTime = buy.getDateTime();
        this.price = buy.getPrice();
        this.card = buy.getCard();
        this.discount = buy.getDiscount();
        this.reservation = new ReservationVO(buy.getReservation());
    }
}
