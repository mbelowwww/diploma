package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import openkino.com.view.Views;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Reservation extends AuditEntity {

    @JsonView(Views.Public.class)
    @Column
    private Boolean status;

    @JsonView(Views.Public.class)
    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime start;

    @JsonView(Views.Public.class)
    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime end;

    @JsonView(Views.Public.class)
    @Column
    private String personName;

    @JsonView(Views.Public.class)
    @Column
    private BigDecimal price;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private KinoUser kinoUser;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private Session session;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<ReservationPlaces> reservationPlaces;

    @JsonView(Views.Public.class)
    @OneToOne
    private Buy buy;

    protected boolean canEqual(final Object other) {
        return other instanceof Reservation;
    }

}
