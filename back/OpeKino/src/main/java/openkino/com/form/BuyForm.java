package openkino.com.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BuyForm {
    private Long id;
    private BigDecimal price;
    private CardForm card;
    private DiscountForm discount;
    private List<ReservationForm> reservations;
}