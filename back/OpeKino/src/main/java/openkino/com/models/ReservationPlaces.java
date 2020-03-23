package openkino.com.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table
public class ReservationPlaces extends AuditEntity{
    @ManyToOne
    @JoinColumn
    private Reservation reservation;

    @ManyToOne
    @JoinColumn
    private Place place;
}
