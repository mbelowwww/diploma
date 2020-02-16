package openkino.com.models;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Buy {
    @Id
    @GeneratedValue
    private Long id;


    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", locale = "ru_RU")
    private LocalDateTime localDateTime = LocalDateTime.now();

    @Column
    private BigDecimal price;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn
    private Card card;

    @ManyToOne
    @JoinColumn
    private Discount discount;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buy")
    private List<Reservation> reservations;

    @JsonIgnore
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
