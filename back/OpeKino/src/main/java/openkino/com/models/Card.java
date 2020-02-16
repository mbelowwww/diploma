package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table
public class Card {

    @Id
    private Long id;

    @Column
    private BigDecimal balance;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn
    private KinoUser kinoUser;

    @OneToMany(mappedBy = "card")
    private List<Buy> buys;

    public Card() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Buy> getBuys() {
        return buys;
    }

    @JsonIgnore
    public void setBuys(List<Buy> buys) {
        this.buys = buys;
    }

    public KinoUser getKinoUser() {
        return kinoUser;
    }

    public void setKinoUser(KinoUser kinoUser) {
        this.kinoUser = kinoUser;
    }

    public class ViewCard {
        public class Public {}
        public class Internal extends Public {}
    }
}
