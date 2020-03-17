package openkino.com.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.models.Reservation;
import openkino.com.view.Views;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservationVO extends ModelVO {
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonView(Views.Public.class)
    private LocalDateTime start;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonView(Views.Public.class)
    private LocalDateTime end;

    @JsonView(Views.Public.class)
    private BigDecimal price;

    @JsonView(Views.Public.class)
    private String personName;

    @JsonView(Views.Public.class)
    private Boolean status;

    @JsonView(Views.Public.class)
    private Long kinoUserId;

    @JsonView(Views.Public.class)
    private SessionVO session;

    public ReservationVO(Reservation reservation) {
        super(reservation.getId());
        this.session = new SessionVO(reservation.getSession());
        this.start = reservation.getStart();
        this.end = reservation.getEnd();
        this.kinoUserId = reservation.getKinoUser() != null ? reservation.getKinoUser().getId() : null;
        this.status = reservation.getStatus();
        this.personName = reservation.getPersonName();
    }
}
