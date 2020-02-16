package openkino.com.form;

import java.math.BigDecimal;

public class CardForm {

    private Long id;
    private BigDecimal balance;

    public CardForm() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
