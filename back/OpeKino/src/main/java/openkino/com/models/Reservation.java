package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
public class Reservation extends AuditEntity {

    @Column
    private ReservationStatusEnum status;

    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime start;

    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime end;

    @Column
    private String personName;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn
    private KinoUser kinoUser;

    @ManyToOne
    @JoinColumn
    private Session session;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<Place> places;

    @OneToOne
    private Buy buy;
}
