package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Reservation {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime start;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime end;

    @Column
    private ReservationStatus status;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn
    private KinoUser kinoUser;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Place place;

    @ManyToOne
    @JoinColumn
    private Buy buy;

    public enum ReservationStatus{
        booked,notBooked
    }

}
