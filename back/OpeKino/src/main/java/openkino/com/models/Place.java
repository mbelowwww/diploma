package openkino.com.models;

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
public class Place {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @JsonView(Views.Public.class)
    @Column
    private Boolean status = true;

    @JsonView(Views.Public.class)
    @Column
    private Integer y;

    @JsonView(Views.Public.class)
    @Column
    private Integer x;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn
    private Hall hall;

}
