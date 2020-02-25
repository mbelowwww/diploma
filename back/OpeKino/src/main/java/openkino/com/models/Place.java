package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table
public class Place extends AuditEntity {

    @JsonView(Views.Public.class)
    @Column
    private Boolean status = true;

    @JsonView(Views.Public.class)
    @Column
    private Integer y;

    @JsonView(Views.Public.class)
    @Column
    private Integer x;

    @ManyToOne
    @JoinColumn
    private Reservation reservation;

    @ManyToOne
    @JoinColumn
    private Hall hall;


    @JsonIgnore
    public void setHall(Hall hall) {
        this.hall = hall;
    }

}
